package com.lucidity.deliveryoptimizer.controller;

import com.lucidity.deliveryoptimizer.service.RoutingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RouteControllerTest {

    private MockMvc mockMvc;
    private RoutingService routingService;

    @BeforeEach
    void setUp() {
        routingService = mock(RoutingService.class);
        RouteController controller = new RouteController(routingService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnRouteFromApi() throws Exception {
        when(routingService.calculateOptimalRoute(any(), any())).thenReturn("Route 1 (R1 -> R2 -> C1 -> C2)");

        mockMvc.perform(get("/api/route/best").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("Route 1 (R1 -> R2 -> C1 -> C2)"));

        verify(routingService, times(1)).calculateOptimalRoute(any(), any());
    }
}