import { Slider } from "@/components/ui/slider"

import Example from '@/components/Chart'
import Call from '@/components/Call'

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24 bg-foreground">
      <div className='text-3xl font-bold text-white'>
        Welcome to SafeImpute

      </div>
      <div className='w-full h-full'>
      <div className='flex justify-center'>
        <Example/>
      </div>
      <Slider defaultValue={[33]} max={100} step={1} />
      <Call />


      </div>

    </main>
  )
}
