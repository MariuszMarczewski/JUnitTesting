package pl.mmarczewski.testing.cart;

import pl.mmarczewski.testing.order.OrderStatus;

class CartService {

    private CartHandler cartHandler;

    CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.PREPARING);
            });
            return cart;
        } else {
            cart.getOrders().forEach(order -> {
                order.changeOrderStatus(OrderStatus.REJECTED);
            });
            return cart;
        }
    }

}
