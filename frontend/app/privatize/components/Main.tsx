'use client'
import { Slider } from "@/components/ui/slider"

import Chart from '@/app/privatize/components/Chart'
import Call from '@/app/privatize/components/Call'
import Post from '@/app/privatize/components/Post'
import Settings from "./Settings"
import Summary from "./Summary"
import { useState } from "react"


const Main = () => {

  const [chartData, setChartData] = useState<{x: number, y: number}[]>([])

  const onResponseOk = async () => {
    try {
      console.log("TRYING 1")
      const response = await fetch("http://localhost:8080/points")
      console.log("TRYING")
      if (response.ok) {
        console.log("WORKS")
        const data = await response.json()
        const dataArray = Object.entries(data)
                                .map(([x, y]) => ({x, y}))
                                .sort((a, b) => a.x - b.x)
        setChartData(dataArray)
        console.log(dataArray)
      }
    } catch (error) {
      console.log("error fetching chart data", error)
    }
  }

  return (


      <div className='w-full h-full max-w-6xl'>
        <div className="w-full flex flex-row justify-between text-secondary">
          <Settings onResponseOk={onResponseOk}/>
          <div className="bg-primary border border-background/20 rounded-sm align-middle pt-5 pr-10 pl-0 mx-auto w-full flex  text-center ">
            <div className="-rotate-90 h-min text-secondary my-auto -mr-6">Accuracy</div>
            <div className="flex flex-col mb-4">
              <div className="text-left font-semibold text-2xl mb-2">
                % Noise VS Accuracy
              </div>
              <Chart chartData={chartData}/>
              <div className="text-secondary">% Noise</div>
            </div>

          </div>
        </div>
        <div className="pt-6 w-full">
          <Summary />
        </div>



        <div className='flex justify-center'>
        </div>
        {/* <Slider defaultValue={[33]} max={100} step={1} /> */}
        {/* <Call />
        <Post />  */}


      </div>

    
  )
}


export default Main;