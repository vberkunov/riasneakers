package com.shopservice.riasneakers.services.serviceImpl;

import com.shopservice.riasneakers.dto.ItemDto;
import com.shopservice.riasneakers.dto.response.ItemResponse;
import com.shopservice.riasneakers.dto.response.MessageResponse;
import com.shopservice.riasneakers.entity.Item;
import com.shopservice.riasneakers.repository.ItemRepository;
import com.shopservice.riasneakers.repository.PhotoRepository;
import com.shopservice.riasneakers.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

    Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class.getSimpleName());
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public ItemResponse saveItem(ItemDto dto) {
        Item item = new Item(dto.getTitle(),dto.getPriceBeg(),dto.getDescription(),dto.getBrand(), dto.getPhoto(), dto.getPriceFinal());
        Item itemFromDb =  itemRepository.save(item);

        return new ItemResponse(itemFromDb.getId());

    }


    @Override
    public ItemResponse getItemById(String id) {
        return null;
    }

}
