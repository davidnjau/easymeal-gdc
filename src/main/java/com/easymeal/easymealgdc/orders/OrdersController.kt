package com.easymeal.easymealgdc.orders

import com.easymeal.easymealgdc.DbOrder
import com.easymeal.easymealgdc.DbPaymentDetails
import com.easymeal.easymealgdc.FormatterHelper
import com.easymeal.easymealgdc.Results
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders/api/v1/")
class OrdersController(private val orderService: OrderService, private val formatterHelper: FormatterHelper) {

    @GetMapping("current-orders")
    fun getCurrentOrders(
            @RequestParam(defaultValue = "date") sortBy: String?,
            @RequestParam(defaultValue = "") filter: String?): ResponseEntity<*> {
        val results: Results = orderService.getCurrentOrders(sortBy, filter)

        return formatterHelper.getResponseEntity(results)
    }
    @GetMapping("view-order/{orderId}")
    fun viewOrderDetails(@PathVariable orderId: String): ResponseEntity<*> {
        val results: Results = orderService.viewOrderDetails(orderId)
        return formatterHelper.getResponseEntity(results)
    }
    @PostMapping("process-order/{orderId}")
    fun processOrder(
            @PathVariable orderId: String,
            @RequestParam status: String): ResponseEntity<*> {
        val results: Results = orderService.processOrder(orderId, status)
        return formatterHelper.getResponseEntity(results)
    }
    @PostMapping("create-order")
    fun createOrder(
            @RequestBody order: DbOrder): ResponseEntity<*> {
        val results: Results = orderService.createOrder(order)
        return formatterHelper.getResponseEntity(results)
    }
    @PostMapping("pay-order")
    fun payOrder(
            @RequestBody dbPaymentDetails: DbPaymentDetails): ResponseEntity<*> {
        val results: Results = orderService.payOrder(dbPaymentDetails)
        return formatterHelper.getResponseEntity(results)
    }

}