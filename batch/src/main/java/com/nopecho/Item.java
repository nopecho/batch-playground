package com.nopecho;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {

    private Long id;
    private String idToken;

    public void changeToken(){
        this.idToken = "change";
    }
}
