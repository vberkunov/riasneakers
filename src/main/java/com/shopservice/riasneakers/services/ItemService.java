package com.shopservice.riasneakers.services;

import com.shopservice.riasneakers.dto.response.ItemResponse;
import com.shopservice.riasneakers.dto.response.MessageResponse;
import org.apache.commons.codec.EncoderException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface ItemService {


    ItemResponse getItemById(String id);



}
