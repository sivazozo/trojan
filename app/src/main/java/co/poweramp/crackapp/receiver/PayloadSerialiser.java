package co.poweramp.crackapp.receiver;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * CrackApp
 * <p/>
 * Created by duncan on 5/1/16.
 * Copyright (c) 2015 Duncan Leo. All Rights Reserved.
 */
public class PayloadSerialiser implements JsonSerializer<Payload> {
    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";

    @Override
    public JsonElement serialize(Payload src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("account_id", src.getAccountId());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        obj.addProperty("timestamp", sdf.format(new Date(src.getTimestamp())));
        obj.addProperty("audio_url", src.getAudio());
        obj.addProperty("image_url", src.getImage());
//        obj.addProperty("account", src.getAccounts()[0].name);
        JsonObject location = new JsonObject();
        location.addProperty("lat", src.getLocation().getLatitude());
        location.addProperty("lon", src.getLocation().getLongitude());
        obj.add("location", location);
        return obj;
    }
}
