package com.shopservice.riasneakers.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResponse {
        private String name;
        private String url;
        private String type;
        private long size;

        public PhotoResponse(String name, String url, String type, long size) {
            this.name = name;
            this.url = url;
            this.type = type;
            this.size = size;
        }



}
