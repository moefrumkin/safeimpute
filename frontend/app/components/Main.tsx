
import { Slider } from "@/components/ui/slider"

import Chart from '@/app/components/Chart'
import Call from '@/app/components/Call'
import Post from '@/app/components/Post'
import Settings from "./Settings"


const Main = () => {

  return (

      <div className='w-full h-full max-w-6xl'>
        <div className="w-full flex flex-row">
          <Settings />
          <div className="bg-primary border border-background/20 rounded-sm p-10 mx-auto max-w-3xl w-full">
            <Chart />
          </div>
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