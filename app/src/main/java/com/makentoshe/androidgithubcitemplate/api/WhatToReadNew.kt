package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WhatToReadNew {
    @GET("/api/titles/") // /api/titles/?ordering=top&section=monthly&last_days=21&count=10
    fun getInARow(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") //  /api/titles/?ordering=top&categories=5&section=monthly&last_days=21&count=10
    fun getWeb(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("categories")
        categories: Int = 5,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") //  /api/titles/?ordering=top&type=0&section=monthly&last_days=21&count=10
    fun getManga(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("type")
        type: Int = 0,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") // /api/titles/?ordering=top&type=4&section=monthly&last_days=21&count=10
    fun getAuthors(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("type")
        type: Int = 4,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") // /api/titles/?ordering=top&categories_or=13,115&section=monthly&last_days=21&count=10
    fun getSecondLife(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("categories_or")
        categories_or: String = "13,115",
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") // /api/titles/?ordering=top&genres=25&section=monthly&last_days=21&count=10
    fun getRomantic(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("genres")
        genres: Int = 25,  // genre's id
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    )

    @GET("/api/titles/") //  /api/titles/?ordering=top&genres_or=28,9&section=monthly&last_days=21&count=10
    fun getForLadies(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("genres_or")
        genres_or: String = "28,9",
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") // /api/titles/?ordering=top&categories=18&section=monthly&last_days=21&count=10
    fun getKult(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("categories")
        categories: Int = 18,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") //  /api/titles/?ordering=top&genres=50&section=monthly&last_days=21&count=10
    fun getComedy(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("genres")
        genres: Int = 50,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

    @GET("/api/titles/") // /api/titles/?ordering=top&categories=91&section=monthly&last_days=21&count=10
    fun getSystem(
        @Query("ordering")
        ordering: String = "top",
        @Query("count")
        count: Int = 10,
        @Query("categories")
        categories: Int = 91,
        @Query("last_days")
        last_days: Int = 21,
        @Query("section")
        section: String = "monthly"
    ): Call<ResponseBody>

}