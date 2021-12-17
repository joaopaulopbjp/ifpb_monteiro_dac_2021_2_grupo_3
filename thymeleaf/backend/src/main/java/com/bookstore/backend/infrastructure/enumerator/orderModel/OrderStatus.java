package com.bookstore.backend.infrastructure.enumerator.orderModel;

import com.bookstore.backend.infrastructure.exception.NotFoundException;

public enum OrderStatus {

    PROCESSING(1l), 
    TRANSPORT(2l), 
    FINISHED(3l), 
    CANCELED(4l);

    private Long status;

    private OrderStatus(Long valor){
        this.status = valor;
    }

    public Long getStatus(){
        return this.status;
    }

    public static OrderStatus getById(Long id) throws NotFoundException{
        for(OrderStatus status: OrderStatus.values()){
            if(status.getStatus() == id){
                return status;
            }
        }
        throw new NotFoundException("Not found Enum " + id);
    }
}
