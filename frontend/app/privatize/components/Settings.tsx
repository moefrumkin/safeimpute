import { useRef, useState } from "react";

import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"



type SettingsProps = {
  buttonHandler: (n: string) => void;
};




const Settings = ({ buttonHandler }: SettingsProps) => {
  const minPrivacyRef = useRef<HTMLInputElement>(null);
  const maxPrivacyRef = useRef<HTMLInputElement>(null);
  const numStepsRef = useRef<HTMLInputElement>(null);

  const validateInput = (data: Object) => {
    return (1)
  }

  const invalidDataHandler = () => {

  }

  const handleClick = async () => {

    const inputData = {
      "minPrivacy": minPrivacyRef.current.value,
      "maxPrivacy": maxPrivacyRef.current.value,
      "numSteps": maxPrivacyRef.current.value,
    }

    const validated = validateInput(inputData)
    
    if (validated) {
      try {
        const response = await fetch (
          "http://localhost:8080/parametersUpload",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(inputData)
          }
        )
        if (response.ok) {
          console.log("data posted successfully")
        }  
      } catch(error) {
        console.log("error posting data: ", error)
      }
      if(minPrivacyRef.current) {
        buttonHandler(minPrivacyRef.current.value)
      }
    } else {
      invalidDataHandler()
    }
  }

  const [toggle, setToggle] = useState(false)

  const toggleHandler = () => {
    if(toggle) {
      setToggle(false)
    } else {
      setToggle(true)
    }
  }

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
            <input ref={minPrivacyRef} className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0" ></input>
            {toggle && (
            <div className="z-50  rounded-md bg-black/20 text-popover-foreground shadow-md outline-none animate-in  fade-in-0  zoom-in-80 slide-in-from-left-2 absolute ml-72  p-1 text-sm font-light text-red-800 border-red-800 border">please enter a valid value</div>
            )}
          </div>
          <div className="flex">
            <p className="w-1/3 left-0">Max. Privacy</p>
            <input ref={maxPrivacyRef} className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0"></input>
          </div>
          <div className="flex">
            <p className="w-1/3 left-0">Num Steps</p>
            <input ref={numStepsRef} className="ml-6 p-1 rounded-sm bg-white/10 border w-1/2 left-0 "></input>
          </div>
          <button className="left-0 border border-black/20 bg-black/20 hover:border-white/50 p-2 rounded-sm transition duration-200" onClick={handleClick}>confirm</button>

        </div>
        <button onClick={toggleHandler}>test</button>

      </div>
      <div>
      </div>
    </div>
  )

}

export default Settings