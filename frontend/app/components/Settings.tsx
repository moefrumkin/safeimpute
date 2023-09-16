





const Settings = () => {

  return (
    <div className="h-full text-secondary">
      <div className="h-full bg-primary border border-background/20 rounded-sm justify-center p-4 max-w-md min-w-max mr-6">
        <div className="font-semibold text-2xl">
          Settings
        </div>
        <div className="opacity-20 text-md">
          Configure your privacy.
        </div>
        <div className="grid grid-rows-3 gap-4 pt-8 max-w-xl">
          <div className="flex">
            <p className="w-1/3 left-0">Min. Privacy</p>
            <input className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0"></input>
          </div>
          <div className="flex">
            <p className="w-1/3 left-0">Max. Privacy</p>
            <input className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0"></input>
          </div>
          <div className="flex">
            <p className="w-1/3 left-0">Num Steps</p>
            <input className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0 "></input>
          </div>
          <button className="left-0 border border-black/20 bg-black/20 hover:border-white/50 p-2 rounded-sm transition duration-200">confirm</button>

        </div>
      </div>
      <div>
      </div>
    </div>
  )

}

export default Settings