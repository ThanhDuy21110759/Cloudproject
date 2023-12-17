<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 1 cart product -->
<div class="flex items-center hover:bg-gray-100 px-6 py-5">
    <!-- product img -->
    <div class="flex w-full"> <!-- product -->
        <div class="w-20">
            <img class="h-24" src="data:image/jpg;base64,${item.getProduct().getProductimgBase64()}" alt="product-img">
        </div>
        <div class="flex flex-col justify-between ml-4 flex-grow">
            <span class="font-bold text-sm">${item.getProduct().getProducttittle()}</span>
            <span class="text-red-500 text-xs">${item.getProduct().getCategoryName()}</span>
            <a href="#">
                <button class="font-semibold hover:bg-blue-700 text-white text-xs bg-blue-400 rounded-lg mr-44 py-1 p-2">Remove</button>
            </a>
        </div>
    </div>
    <!-- Price -->
    <span class="text-end w-full font-semibold text-sm">Thành tiền: ${item.getProduct().getProductprice() * item.getAmount()}</span>
</div>
