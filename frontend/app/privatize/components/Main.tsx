'use client'
import { Slider } from "@/components/ui/slider"

import Chart from '@/app/privatize/components/Chart'
import Call from '@/app/privatize/components/Call'
import Post from '@/app/privatize/components/Post'
import Settings from "./Settings"
import Summary from "./Summary"
import { useState } from "react"


const Main = () => {
  const [num, setNum] = useState<number>(0)

  const buttonHandler = (n: string) => {
    const parsedInt = parseInt(n, 10);
    if (!isNaN(parsedInt)) {
      setNum(parsedInt);
    }
  }

  return (


      <div className='w-full h-full max-w-6xl'>
        <div className="w-full flex flex-row justify-between">
          <Settings buttonHandler={buttonHandler}/>
          <div className="bg-primary border border-background/20 rounded-sm p-10 mx-auto w-full">
            <Chart chartVals={num}/>
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