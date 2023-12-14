<script>
  let points = 120;
</script>

<div class="bg-slate-100 flex flex-col items-center pt-3.5 px-16 max-md:px-5">
  <div class="flex w-full max-w-[1188px] flex-col mb-20 max-md:max-w-full max-md:mb-10">
    <div class="bg-zinc-300 flex items-center gap-4 pl-4 pr-2.5 py-2.5 rounded-3xl self-end">
      <div class="text-black text-lg grow whitespace-nowrap my-auto">
        Points:
      </div>
      <div class="bg-neutral-400 self-stretch flex items-stretch justify-between gap-5 pl-3 pr-8 py-2.5 rounded-3xl max-md:pr-5">
        <img
          loading="lazy"
          srcset="/trashbin.svg"
          alt="trashbin"
          class="aspect-square object-contain object-center w-[21px] overflow-hidden shrink-0 max-w-full"
        />
        <div class="text-black text-lg self-center my-auto">{points}</div>
      </div>
    </div>
    <div class="text-black text-2xl font-bold ml-7 mt-1.5 max-md:ml-2.5">
      Dashboard
    </div>
    <div class="w-1120 h-376 flex-shrink-0 bg-gradient-to-r from-blue-950 to-violet-800	 rounded-lg mt-1 self-stretch flex flex-col justify-center items-center px-16 py-12 rounded-3xl max-md:max-w-full max-md:px-5">
      <div class="flex w-[494px] max-w-full flex-col items-stretch mt-10 mb-6 max-md:mt-10">
        <div class="text-white text-5xl font-bold self-center whitespace-nowrap max-md:text-4xl">
          Keep It Up!
        </div>
        <div class="text-white text-xl self-center whitespace-nowrap mt-5">
          Keep studying to gain more points.
        </div>
        <button class="bg-white  flex  text-2xl items-center justify-center shrink-0 h-[49px] flex-col mt-20 rounded-2xl max-md:max-w-full max-md:mt-10" onclick="window.location.href = './my_library'">
          My Library
        </button>
      </div>
    </div>
    <div class="self-stretch mt-7 max-md:max-w-full">
      <div class="gap-5 flex max-md:flex-col max-md:items-stretch max-md:gap-0">
        <div class="flex flex-col items-stretch w-[45%] max-md:w-full max-md:ml-0">
          <div class="text-black text-2xl font-bold whitespace-nowrap bg-white grow w-full mt-1.5 pl-9 pr-16 pt-5 pb-80 rounded-[30px] items-start max-md:max-w-full max-md:mt-10 max-md:pb-10 max-md:px-5">
            Recent Folders
          </div>
        </div>
        <div class="flex flex-col items-stretch w-[55%] ml-5 max-md:w-full max-md:ml-0">
          <div class="text-black text-2xl font-bold max-w-[700px] bg-white grow w-full pl-10 pr-16 pt-6 pb-80 rounded-[30px] items-start max-md:max-w-full max-md:mt-10 max-md:pb-10 max-md:px-5">
            Studyset Statistics
          </div>
        </div>
      </div>
    </div>
  </div>
</div>