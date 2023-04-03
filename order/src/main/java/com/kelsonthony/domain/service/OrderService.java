package com.kelsonthony.domain.service;

import com.kelsonthony.api.dto.CustomerDTO;
import com.kelsonthony.api.dto.OrderDTO;
import com.kelsonthony.client.CustomerClient;
import com.kelsonthony.client.ProductClient;
import com.kelsonthony.domain.model.OrderEntity;
import com.kelsonthony.domain.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {
    @Inject
    private OrderRepository orderRepository;

    @Inject
    @RestClient
    private CustomerClient customerClient;

    @Inject
    @RestClient
    private ProductClient productClient;

    public List<OrderDTO> getAllOrder() {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        orderRepository.findAll().stream().forEach(item -> {
            orderDTOs.add(MapEntityToDTO(item));
        });

        return orderDTOs;
    }

    public void saveNewOrder(OrderDTO orderDTO) {

        CustomerDTO customerDTO = customerClient.getCustomerById(orderDTO.getCustomerId());

        if (customerDTO.getName().equals(orderDTO.getCustomerName())
                && productClient.getProductById(orderDTO.getProductId()) != null) {
            orderRepository.persist(mapDTOToEntity(orderDTO));
        } else {
            throw new NotFoundException();
        }
    }

    private OrderEntity mapDTOToEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomerId(orderDTO.getCustomerId());
        orderEntity.setCustomerName(orderDTO.getCustomerName());
        orderEntity.setProductId(orderDTO.getProductId());
        orderEntity.setOrderValue(orderDTO.getOrderValue());

        return orderEntity;
    }

    private OrderDTO MapEntityToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(orderEntity.getOrderId());
        orderDTO.setCustomerId(orderEntity.getCustomerId());
        orderDTO.setCustomerName(orderEntity.getCustomerName());
        orderDTO.setProductId(orderEntity.getProductId());
        orderDTO.setOrderValue(orderEntity.getOrderValue());

        return orderDTO;
    }
}
