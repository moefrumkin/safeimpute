import RainLetters from "@/components/RainLetters"



const Home = () => {


  return (

    <div className="flex min-h-screen flex-col items-center justify-between p-16 bg-foreground text-secondary">
      <div className="max-w-6xl">
        <div className="w-1/2">
        <RainLetters/>
        </div>
        <canvas id="Matrix" className="bg-blue-400"></canvas>
        <div className="grid grid-cols-2 gap-12">
          <div className="">
            <div className="text-4xl font-bold">
              Keeping genetic data safe.
            </div>
            <ul className="ml-2 mt-4">
              <li>protecting imputatated datasets through differential privacy</li>
            </ul>
          </div>
          <div>

          </div>
        </div>
      </div>

    </div>
  )
}

export default Home