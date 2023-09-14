package com.easymeal.easymealgdc.orders;

import com.easymeal.easymealgdc.DbOrder;
import com.easymeal.easymealgdc.DbPaymentDetails;
import com.easymeal.easymealgdc.Results;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @NotNull
    @Override
    public Results getCurrentOrders(@Nullable String sortBy, @Nullable String filter) {
        return null;
    }

    @NotNull
    @Override
    public Results viewOrderDetails(@NotNull String orderId) {
        return null;
    }

    @NotNull
    @Override
    public Results processOrder(@NotNull String orderId, @NotNull String status) {
        return null;
    }

    @NotNull
    @Override
    public Results createOrder(@NotNull DbOrder dbOrder) {
        return null;
    }

    @NotNull
    @Override
    public Results payOrder(@NotNull DbPaymentDetails dbPaymentDetails) {
        return null;
    }
}
