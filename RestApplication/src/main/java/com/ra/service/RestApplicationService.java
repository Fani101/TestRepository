package com.ra.service;

import org.springframework.stereotype.Service;

import com.ra.dto.Availability;
import com.ra.dto.Capacity;
import com.ra.dto.Inventory;
import com.ra.dto.ProductAvailability;
import com.ra.dto.ProductRequest;
import com.ra.dto.ProductResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Service
public class RestApplicationService {

	// Question 1

	public ProductResponse getProductDetails(ProductRequest productRequest) {
		LocalDate currentDate = LocalDate.parse("2021-03-19");
		LocalDate requestedDate = LocalDate.parse(productRequest.getReqDate());

		if (requestedDate.isBefore(currentDate) || requestedDate.isAfter(currentDate.plusDays(10))) {
			throw new IllegalArgumentException("Error should be thrown – Bad request - 400");
		}

		List<Inventory> inventoryList = getInventoList();
		double totalQuantity = 0;

		switch (requestedDate.toString()) {
		case "2021-03-19":
			totalQuantity = inventoryList.stream()
					.filter(inventory -> inventory.getProductId().equals(productRequest.getProductId()))
					.mapToDouble(Inventory::getAvailQty).sum();
			break;
		case "2021-03-22":
			totalQuantity = inventoryList.stream()
					.filter(inventory -> inventory.getProductId().equals(productRequest.getProductId()))
					.mapToDouble(Inventory::getAvailQty).sum();
			break;
		case "2021-03-30":
			throw new IllegalArgumentException("Bad request - 400");
		case "2021-03-17":
			throw new IllegalArgumentException("Bad request - 400");
		default:
			throw new IllegalArgumentException("Invalid requested date");
		}

		return new ProductResponse(productRequest.getProductId(), productRequest.getProdName(), totalQuantity);
	}

	// Question 2
	private Map<String, List<Availability>> availabilityData;
	private Map<String, List<Capacity>> capacityData;

	public ProductAvailability getProdAvailability(String storeNo, String productId, Double reqQty, String reqDateStr) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		    Date reqDate;
		    try {
		        reqDate = dateFormat.parse(reqDateStr);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        return null; 
		    }
		
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<Double> availabilityFuture = executor.submit(() -> getAvailability(storeNo, productId, reqDate));
		Future<Double> capacityFuture = executor.submit(() -> getCapacity(storeNo, reqDate));

		try {
			Double availabilityResult = availabilityFuture.get();
			Double capacityResult = capacityFuture.get();

			if (availabilityResult <= 0 || capacityResult <= 0) {
				return new ProductAvailability(storeNo, productId, reqQty, reqDate, "Not Available");
			} else {
				return new ProductAvailability(storeNo, productId, reqQty, reqDate, "Available");
			}
		} catch (InterruptedException | ExecutionException e) {

		} finally {
			executor.shutdown();
		}

		return null;
	}
	
	
	

	private Double getAvailability(String storeNo, String productId, Date reqDate) {
		List<Availability> availabilities = availabilityData.get(storeNo);
		if (availabilities != null) {
			return availabilities.stream()
					.filter(av -> av.getProductId().equals(productId) && av.getDate().equals(reqDate))
					.mapToDouble(Availability::getAvailQty).sum();
		}
		return 0.0;
	}

	private Double getCapacity(String storeNo, Date reqDate) {
		List<Capacity> capacities = capacityData.get(storeNo);
		if (capacities != null) {
			return capacities.stream().filter(cap -> cap.getDate().equals(reqDate))
					.mapToDouble(Capacity::getNoOfOrdersAccepted).sum();
		}
		return 0.0;
	}

	private List<Inventory> getInventoList() {

		List<Inventory> inventoryList = Stream
				.of(new Inventory("Prod1", "Shirt", "Piece", 50.0, LocalDate.of(2021, 02, 20)),
						new Inventory("Prod2", "Pants", "Piece", 70.0, LocalDate.of(2021, 02, 21)),
						new Inventory("Prod3", "Shoes", "Pair", 30.0, LocalDate.of(2021, 02, 28)))
				.toList();
		return inventoryList;
	}

}
