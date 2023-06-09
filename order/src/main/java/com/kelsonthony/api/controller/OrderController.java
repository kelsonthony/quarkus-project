package com.kelsonthony.api.controller;

import com.kelsonthony.api.dto.OrderDTO;
import com.kelsonthony.domain.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/orders")
public class OrderController {

    @Inject
    private OrderService orderService;

    @GET
    @RolesAllowed({"user", "admin"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrder();
    }

    @POST
    @RolesAllowed({"user", "admin"})
    @Transactional
    public Response saveNewOrder(OrderDTO orderDTO) {
        try {
            orderService.saveNewOrder(orderDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
