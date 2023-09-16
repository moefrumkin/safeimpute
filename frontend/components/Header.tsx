import { Divide } from "lucide-react";
import { ModeToggle } from "./ModeToggle";



export default function Header () {

  return (
    <div className="h-full w-full bg-foreground">
      <div className='text-3xl font-bold text-secondary flex items-center justify-between p-16 pb-4  max-w-5xl mx-auto'>
        <h1 className="">
          Welcome to SafeImpute
        </h1>
        <ModeToggle/>
      </div>
    </div>

  )
}