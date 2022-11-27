package edu.mum.cs544;

import org.springframework.stereotype.Component;

@Component
public class InventoryService implements IInventoryService {
    @Override
    public int getNumberInStock(int productNumber) {
        return productNumber-200;
    }
}
