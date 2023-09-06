package com.andy.futures;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * This code example illustrates the usage of CompletableFuture as a return value for caching weather information.
 * The WeatherService class provides weather data based on zip codes, utilizing a cache to improve performance.
 * If a request is made for a zip code already present in the cache, the cached data is returned; otherwise,
 * a REST API call is initiated to fetch the data, which is then stored in the cache.
 */

public class CompletableFutureAsReturnValue {

    static class WeatherService {

        record ZipCode(String code) {
        }

        record WeatherInfo(String condition, int temperatureCelsius) {
        }

        private final Map<ZipCode, WeatherInfo> db = new HashMap<>();
        private final Map<ZipCode, WeatherInfo> cache = new HashMap<>();

        public WeatherService() {
            db.put(new ZipCode("12345"), new WeatherInfo("Sunny", 25));
            db.put(new ZipCode("54321"), new WeatherInfo("Rainy", 18));
            db.put(new ZipCode("98765"), new WeatherInfo("Cloudy", 22));
        }


        private Optional<WeatherInfo> cacheLookup(ZipCode zipCode) {
            Optional<WeatherInfo> optionalWeatherInfo = Optional.ofNullable(cache.get(zipCode));
            if (optionalWeatherInfo.isPresent()) {
                System.out.println("WeatherInfo returned from cache");
            }
            return optionalWeatherInfo;
        }

        private WeatherInfo storeInCache(WeatherInfo info, ZipCode zipCode) {
            return cache.putIfAbsent(zipCode, info);
        }

        private CompletableFuture<WeatherInfo> restCall(ZipCode zipCode) {
            Supplier<WeatherInfo> restCall = () -> {
                System.out.println("WeatherInfo returned from API");
                return db.get(zipCode);
            };


            return CompletableFuture.supplyAsync(restCall)
                    .thenApply(weatherInfo -> storeInCache(weatherInfo, zipCode));
        }

        public CompletableFuture<WeatherInfo> check(ZipCode zipCode) {
            return cacheLookup(zipCode)
                    .map(CompletableFuture::completedFuture)
                    .orElseGet(() -> restCall(zipCode));
        }

        public static void main(String[] args) {

            WeatherService weatherService = new WeatherService();

            ZipCode zipCode = new ZipCode("12345");

            weatherService.check(zipCode).join();
            weatherService.check(zipCode).join();
            weatherService.check(zipCode).join();

        }
    }
}
