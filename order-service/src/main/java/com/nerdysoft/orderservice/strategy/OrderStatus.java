package com.nerdysoft.orderservice.strategy;

public enum OrderStatus implements OrderStatusStrategy {
    PENDING {
        @Override
        public OrderStatus next() {
            return OrderStatus.IN_PROGRESS;
        }
    }, IN_PROGRESS {
        @Override
        public OrderStatus next() {
            return OrderStatus.COOKED;
        }
    }, COMPLETED {
        @Override
        public OrderStatus next() {
            return OrderStatus.COMPLETED;
        }
    }, CANCELED {
        @Override
        public OrderStatus next() {
            return OrderStatus.CANCELED;
        }
    }, DELIVERING {
        @Override
        public OrderStatus next() {
            return OrderStatus.COMPLETED;
        }
    }, COOKED {
        @Override
        public OrderStatus next() {
            return OrderStatus.WAITING;
        }
    }, WAITING {
        @Override
        public OrderStatus next() {
            return OrderStatus.COMPLETED;
        }
    }
}
