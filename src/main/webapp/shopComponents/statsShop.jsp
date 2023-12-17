<%@ page language="java" pageEncoding="UTF-8"%>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet"/>
<script src="https://unpkg.com/stimulus/dist/stimulus.umd.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"
        integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<style>
    body {
        font-family: 'Poppins', sans-serif;
    }
</style>

<%@include file="/components/navbar.jsp" %>

<!---->
<div style="display: flex; flex-direction: row">
    <div style="width: 400px; height: 400px; margin: 100px">
        <div class="container">
            <div class="overflow-hidden rounded-lg shadow-md">
                <div class="px-5 py-3 bg-stone-300">
                    <h5 class="font-bold text-stone-600">
                        Thống kê theo ngày trong tuần
                    </h5>
                </div>
                <canvas id="pieChart"></canvas>
            </div>
        </div>
    </div>
    <div>
        <div class="flex flex-col my-24" data-controller="slider">
            <b><h1 class="text-3xl text-gray-900 text-center mb-4">Top 3 Best Seller</h1></b>
            <div class="flex overflow-x-scroll"
                 style="max-width: 800px"
                 data-slider-target="scrollContainer">
                <c:forEach var="product" items="${dsprod}">
                    <div class="swiper-slide mx-4">
                        <div class="relative flex flex-col justify-between border min-h-[60px] rounded-lg bg-white h-[380px] w-[230px]">
                            <a href="productDetailServlet?Id=${product.getProductid()}" style="margin: 10px;">
                                <img src="upload/${product.getProductimg()}.jpg" alt="Product Image">
                            </a>
                            <h2 class="text-xl px-5">${product.getProducttittle()}</h2>
                            <p class="text-cyan-500 mb-2 px-5 ">$${product.getProductprice()}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--Scroll bar -->
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
    </div>
</div>
<script>
    const labelsPie = [];
    const dataPieValues = [];
    <c:forEach var="entry" items="${revenueByDay}">
        labelsPie.push("${entry.key}");
        dataPieValues.push(${entry.value});
    </c:forEach>

    const dataPie = {
        labels: labelsPie,
        datasets: [
            {
                label: 'Traffic',
                data: dataPieValues,
                backgroundColor: [
                    'rgba(63, 81, 181, 0.5)',
                    'rgba(77, 182, 172, 0.5)',
                    'rgba(66, 133, 244, 0.5)',
                    'rgba(156, 39, 176, 0.5)',
                    'rgba(233, 30, 99, 0.5)',
                    'rgba(66, 73, 244, 0.4)',
                    'rgba(66, 133, 244, 0.2)',
                ],
            },
        ],
    };

    const configChartPie = {
        type: "pie",
        data: dataPie,
        options: {},
    };

    var chartPie = new Chart(
        document.getElementById("pieChart"),
        configChartPie
    );
</script>

<div class="container mx-auto">
    <div class="overflow-hidden rounded-lg shadow-md">
        <div class="px-5 py-3 bg-stone-300">
            <h5 class="font-bold text-stone-600">
                Thống kê theo tháng trong năm
            </h5>
        </div>
        <canvas class="p-6" id="barChart"></canvas>
    </div>
</div>
<script>
    const labelsBar = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October'];
    const dataBarValues = [21120, 43403, 25045, 34023, 53065, 19085, 9087, 42034, 34056, 23045];

    //Adding revenue by month
    <c:forEach var="entry" items="${revenueByMonth}">
        labelsBar.push("${entry.key}");
        dataBarValues.push(${entry.value});
    </c:forEach>

    const dataBar = {
        labels: labelsBar,
        datasets: [
            {
                label: 'Doanh Thu',
                data: dataBarValues,
                backgroundColor: [
                    'rgba(63, 81, 181, 0.5)',
                    'rgba(77, 182, 172, 0.5)',
                    'rgba(66, 133, 244, 0.5)',
                    'rgba(156, 39, 176, 0.5)',
                    'rgba(233, 30, 99, 0.5)',
                    'rgba(66, 73, 244, 0.4)',
                    'rgba(66, 133, 244, 0.2)',
                    'rgba(63, 81, 181, 0.5)',
                    'rgba(77, 182, 172, 0.5)',
                    'rgba(66, 133, 244, 0.5)',
                    'rgba(156, 39, 176, 0.5)',
                    'rgba(233, 30, 99, 0.5)',
                ],
            },
        ],
    };

    const configChartBar = {
        type: "bar",
        data: dataBar,
        options: {
            indexAxis: 'y',
        },
    };

    var chartBar = new Chart(
        document.getElementById("barChart"),
        configChartBar
    );
</script>







