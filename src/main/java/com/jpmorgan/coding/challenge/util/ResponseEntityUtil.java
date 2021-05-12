package com.jpmorgan.coding.challenge.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResponseEntityUtil {

    public static URI toUri(String path, long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path)
                .buildAndExpand(id).toUri();
    }

    public static <T> ResponseEntity<T> created(String path, long id, T body) {
        return ResponseEntity.created(toUri(path, id)).body(body);
    }


}
