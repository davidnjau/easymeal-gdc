package com.easymeal.easymealgdc.orders

import com.easymeal.easymealgdc.DbOrder
import com.easymeal.easymealgdc.DbPaymentDetails
import com.easymeal.easymealgdc.Results
import org.springframework.stereotype.Service

interface OrderService {

    fun getCurrentOrders(sortBy:String?, filter:String?):Results
    fun viewOrderDetails(orderId:String):Results
    fun processOrder(orderId:String, status:String):Results
    fun createOrder(dbOrder: DbOrder):Results
    fun payOrder(dbPaymentDetails: DbPaymentDetails):Results

}