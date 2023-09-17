import { Divide } from "lucide-react";
import { ModeToggle } from "./ModeToggle";
import Link from "next/link";



export default function Header () {

  return (
    <div className="h-full w-full bg-foreground border-b border-white/10  drop-shadow-md">
      <div className=' font-bold text-secondary flex items-center justify-between p-16 py-10  max-w-5xl mx-auto '>
        <Link href="/" className="text-3xl">
          Safe Impute
        </Link>

        <div className="flex flex-row justify-evenly grow max-w-md">
          <Link className="hover:border-b" href='/'>Home</Link>
          <Link className="hover:border-b" href='/privatize'>Privatize</Link>
          <Link className="hover:border-b" href='/data'>Data</Link>
        </div>




        <ModeToggle/>




      </div>
    </div>

  )
}