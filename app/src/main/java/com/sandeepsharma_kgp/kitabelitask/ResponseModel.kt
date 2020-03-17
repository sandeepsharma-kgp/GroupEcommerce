package com.sandeepsharma_kgp.kitabelitask

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result (
    @SerializedName("code") val code : String?,
    @SerializedName("message") val message : String?,
    @SerializedName("payload") val payload : List<ResponseModel>

    ) : Parcelable

@Parcelize
data class ResponseModel(
    @SerializedName("itemId") val itemId : Int?,
    @SerializedName("itemDTO") val itemDTO : ItemDTO,
    @SerializedName("groupPrice") val groupPrice : Int?,
    @SerializedName("itemPrice") val itemPrice : Int?,
    @SerializedName("itemGroupId") val itemGroupId : Int?,
    @SerializedName("leaderId") val leaderId : Int?,
    @SerializedName("leaderName") val leaderName : String?,
    @SerializedName("leaderAvatar") val leaderAvatar : String?,
    @SerializedName("userNames") val userNames : List<String>,
    @SerializedName("groupMemberIds") val groupMemberIds : List<Int>,
    @SerializedName("expireAt") val expireAt : Int?,
    @SerializedName("type") val type : String?,
    @SerializedName("category") val category : String?
) : Parcelable

@Parcelize
data class ItemDTO (

    @SerializedName("id") val id : Int?,
    @SerializedName("price") val price : Int?,
    @SerializedName("unitSize") val unitSize : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("sellerName") val sellerName : String?,
    @SerializedName("sellerLocation") val sellerLocation : String?,
    @SerializedName("category") val category : String?,
    @SerializedName("description") val description : Description?,
    @SerializedName("quantity") val quantity : Integer?,
    @SerializedName("rating") val rating : Double?,
    @SerializedName("ratingCount") val ratingCount : Int?,
    @SerializedName("itemCondition") val itemCondition : String?,
    @SerializedName("merchantSuccessTransaction") val merchantSuccessTransaction : String?,
    @SerializedName("merchantItemSold") val merchantItemSold : String?,
    @SerializedName("images") val images : List<String>,
    @SerializedName("breadcrumb") val breadcrumb : String?,
    @SerializedName("trending") val trending : Int?,
    @SerializedName("inStock") val inStock : Boolean,
    @SerializedName("soloPrice") val soloPrice : Int?,
    @SerializedName("groupPrice") val groupPrice : Int?,
    @SerializedName("itemUrl") val itemUrl : String?
) : Parcelable

@Parcelize
data class Description (

    @SerializedName("tokopedia_url") val tokopedia_url : String?,
    @SerializedName("merchantInfo") val merchantInfo : String?,
    @SerializedName("orderInfo") val orderInfo : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("delivery_options") val delivery_options : String?,
    @SerializedName("weight") val weight : String?,
    @SerializedName("review") val review : Review?
//    @SerializedName("variantInfo") val variantInfo : VariantInfo
) : Parcelable

@Parcelize
data class Review (

    @SerializedName("count") val count : Int?,
    @SerializedName("star") val star : Double?
) : Parcelable