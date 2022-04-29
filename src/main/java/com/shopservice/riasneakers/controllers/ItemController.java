package com.shopservice.riasneakers.controllers;


import com.shopservice.riasneakers.dto.ItemDto;
import com.shopservice.riasneakers.dto.response.ItemResponse;
import com.shopservice.riasneakers.services.ItemService;
import com.shopservice.riasneakers.services.serviceImpl.ItemServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class.getSimpleName());
    private final ItemServiceImpl itemService;

    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public ItemResponse saveItem( @RequestBody ItemDto dto) {
        logger.info("Create new nfc card");
        return itemService.saveItem(dto);
    }





}

