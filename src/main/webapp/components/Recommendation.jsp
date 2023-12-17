<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet"/>
<script src="https://unpkg.com/stimulus/dist/stimulus.umd.js"></script>

<style>
    body {
        font-family: 'Poppins', sans-serif;
    }
</style>

<div class="flex flex-col my-24" data-controller="slider">
    <b><h1 class="text-3xl text-gray-900 text-center mb-4">Recommendation Product</h1></b>
    <div class="flex overflow-x-scroll" data-slider-target="scrollContainer">
        <c:forEach var="product" items="${listProduct}">
            <div class="swiper-slide mx-4">
                <div class="relative flex flex-col justify-between border min-h-[60px] rounded-lg bg-white h-[380px] w-[230px]">
                    <a href="productDetailServlet?Id=${product.getProductid()}" style="margin: 10px;">
                        <img src="${pageContext.request.contextPath}/upload/${product.getProductimg()}.jpg" alt="Product Image">
                    </a>
                    <h2 class="text-xl px-5">${product.getProducttittle()}</h2>
                    <p class="text-cyan-500 mb-2 px-5 ">$${product.getProductprice()}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    const application = Stimulus.Application.start()

    application.register("slider", class extends Stimulus.Controller {
        static targets = [ "image", "indicator", "scrollContainer" ]

        scrollTo(event) {
            event.preventDefault()
            const imageId = this.data.get('image-id')
            const imageElement = this.imageTargets.find(el => el.id === imageId)
            this.scrollContainerTarget.scrollLeft = imageElement.offsetLeft
        }
    })
</script>
