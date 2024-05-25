package com.otus.kfl.rcrs.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Area@JsonCreator constructor(
    @JsonProperty("id") val id: String?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("url") val url: String?
)

data class Salary@JsonCreator constructor(
    @JsonProperty("from") val from: Int,
    @JsonProperty("to") val to: Int?,
    @JsonProperty("currency") val currency: String,
    @JsonProperty("gross") val gross: Boolean
)

data class Address@JsonCreator constructor(
    @JsonProperty("city") val city: String?,
    @JsonProperty("street") val street: String?,
    @JsonProperty("building") val building: String?,
    @JsonProperty("lat") val lat: Double?,
    @JsonProperty("lng") val lng: Double?
)

data class Employer@JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String
)

data class Snippet@JsonCreator constructor(
    @JsonProperty("requirement") val requirement: String,
    @JsonProperty("responsibility") val responsibility: String?
)

data class Schedule@JsonCreator constructor(
    @JsonProperty("id") val id: String?,
    @JsonProperty("name") val name: String?
)

data class Experience@JsonCreator constructor(
    @JsonProperty("id") val id: String?,
    @JsonProperty("name") val name: String?
)

data class Item@JsonCreator constructor(
    @JsonProperty("id") val id: String?,
    @JsonProperty("premium") val premium: Boolean,
    @JsonProperty("name") val name: String?,
    @JsonProperty("department") val department: String?,
    @JsonProperty("area") val area: Area,
    @JsonProperty("salary") val salary: Salary?,
    @JsonProperty("address") val address: Address?,
    @JsonProperty("published_at") val publishedAt: String,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("url") val url: String?,
    @JsonProperty("employer") val employer: Employer?,
    @JsonProperty("snippet") val snippet: Snippet?,
    @JsonProperty("contacts") val contacts: String?,
    @JsonProperty("schedule") val schedule: Schedule?,
    @JsonProperty("experience") val experience: Experience?
)

data class HHModelApi @JsonCreator constructor(
    @JsonProperty("items") val items: List<Item>
)
