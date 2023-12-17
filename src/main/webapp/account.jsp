<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Account</title>

    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/07e390e8c9.js" crossorigin="anonymous"></script>
</head>
<body>
<%@include file="components/navbar.jsp" %>

<div class="bg-light/30 draggable relative mb-6 flex w-full min-w-0 flex-col break-words rounded-2xl border border-dashed border-stone-200 bg-clip-border">
    <!-- card body -->
    <div class="min-h-[70px] flex-auto bg-transparent px-9 pb-0 pt-9">
        <div class="mb-6 flex flex-wrap xl:flex-nowrap">
            <div class="mb-5 mr-5">
                <div class="relative inline-block shrink-0 rounded-2xl">
                    <img class="inline-block h-[80px] w-[80px] shrink-0 rounded-2xl lg:h-[160px] lg:w-[160px]" src="https://raw.githubusercontent.com/Loopple/loopple-public-assets/main/riva-dashboard-tailwind/img/avatars/avatar1.jpg" alt="image" />

                    <div class="group/tooltip relative">
                        <span class="absolute bottom-0 end-0 -mb-1 -mr-2 h-[15px] w-[15px] rounded-full border border-white bg-green-500"></span>
                        <span class="text-secondary-inverse absolute bottom-0 start-full z-10 -mb-2 ml-4 block transform whitespace-nowrap rounded-2xl bg-white px-3 py-2 text-center text-xs font-medium opacity-0 shadow-sm transition-opacity duration-300 ease-in-out group-hover/tooltip:opacity-100"> Status: Active </span>
                    </div>
                </div>
            </div>
            <div class="grow">
                <div class="mb-2 flex flex-wrap items-start justify-between">
                    <div class="flex flex-col">
                        <div class="mb-2 flex items-center">
                            <a class="text-secondary-inverse hover:text-primary mr-1 text-[1.5rem] font-semibold transition-colors duration-200 ease-in-out" href="javascript:void(0)"> Alec Jhonson </a>
                        </div>
                        <div class="mb-4 flex flex-wrap pr-2 font-medium">
                            <a class="text-secondary-dark hover:text-primary mb-2 mr-5 flex items-center" href="javascript:void(0)">
                                <span class="mr-1"> <i class="fa-solid fa-location-dot"></i> </span>
                                Location: Ho Chi Minh
                            </a>

                            <a class="text-secondary-dark hover:text-primary mb-2 mr-5 flex items-center" href="javascript:void(0)">
                                <span class="mr-1"> <i class="fa-solid fa-shop"></i> </span>
                                Products: 100
                            </a>

                            <a class="text-secondary-dark hover:text-primary mb-2 mr-5 flex items-center" href="javascript:void(0)">
                                <span class="mr-1"> <i class="fa-brands fa-rocketchat"></i> </span>
                                Reply: 90%
                            </a>

                            <a class="text-secondary-dark hover:text-primary mb-2 mr-5 flex items-center" href="javascript:void(0)">
                                <span class="mr-1"> <i class="fa-solid fa-star"></i> </span>
                                Rate: 4.6
                            </a>
                        </div>
                    </div>
                    <div class="my-auto flex flex-wrap">
                        <button href="javascript:void(0)" class="hover:bg-light-dark active:bg-light-dark focus:bg-light-dark mr-3 inline-block cursor-pointer rounded-2xl border bg-blue-200 px-6 py-3 text-center align-middle text-base font-medium leading-normal shadow-none transition-colors duration-150 ease-in-out">Follow</button>
                    </div>
                </div>
                <div class="flex flex-wrap justify-between">
                    <div class="flex flex-wrap items-center">
                        <a href="javascript:void(0)" class="text-secondary-inverse mb-2 mr-3 inline-flex items-center justify-center rounded-full bg-neutral-100 px-3 py-1 text-sm font-medium leading-normal transition-all duration-200 ease-in-out hover:bg-neutral-200"> 320 Following </a>
                        <a href="javascript:void(0)" class="text-secondary-inverse mb-2 mr-3 inline-flex items-center justify-center rounded-full bg-neutral-100 px-3 py-1 text-sm font-medium leading-normal transition-all duration-200 ease-in-out hover:bg-neutral-200"> 2.5k Followers </a>
                        <a href="javascript:void(0)" class="text-secondary-inverse mb-2 mr-3 inline-flex items-center justify-center rounded-full bg-neutral-100 px-3 py-1 text-sm font-medium leading-normal transition-all duration-200 ease-in-out hover:bg-neutral-200"> 48 Deals </a>
                    </div>
                </div>
            </div>
        </div>

        <hr class="h-px w-full border-neutral-200" />

        <ul nav-tabs class="active-assignments group flex list-none flex-wrap items-stretch border-b-2 border-solid border-transparent text-[1.15rem] font-semibold">
            <li class="-mb-[2px] mt-2 flex">
                <a aria-controls="summary" class="group-[.active-summary]:border-primary group-[.active-summary]:text-primary text-muted hover:border-primary mr-1 border-b-2 border-transparent py-5 transition-colors duration-200 ease-in-out sm:mr-3 lg:mr-10" href="javascript:void(0)"> Dạo </a>
            </li>
            <li class="-mb-[2px] mt-2 flex">
                <a aria-controls="assignments" class="group-[.active-assignments]:border-primary group-[.active-assignments]:text-primary text-muted hover:border-primary mr-1 border-b-2 border-transparent py-5 transition-colors duration-200 ease-in-out sm:mr-3 lg:mr-10" href="javascript:void(0)"> Tất Cả Sản Phẩm </a>
            </li>
            <li class="-mb-[2px] mt-2 flex">
                <a aria-controls="marketing" class="group-[.active-marketing]:border-primary group-[.active-marketing]:text-primary text-muted hover:border-primary mr-1 border-b-2 border-transparent py-5 transition-colors duration-200 ease-in-out sm:mr-3 lg:mr-10" href="javascript:void(0)"> Category-1 </a>
            </li>
            <li class="-mb-[2px] mt-2 flex">
                <a aria-controls="followers" class="group-[.active-followers]:border-primary group-[.active-followers]:text-primary text-muted hover:border-primary mr-1 border-b-2 border-transparent py-5 transition-colors duration-200 ease-in-out sm:mr-3 lg:mr-10" href="javascript:void(0)"> Category-2 </a>
            </li>
            <li class="group -mb-[2px] mt-2 flex">
                <a aria-controls="history" class="group-[.active-history]:border-primary group-[.active-history]:text-primary text-muted hover:border-primary mr-1 border-b-2 border-transparent py-5 transition-colors duration-200 ease-in-out sm:mr-3 lg:mr-10" href="javascript:void(0)"> Category-3 </a>
            </li>
        </ul>
    </div>
</div>

</body>
</html>
