import RainLetters from "@/components/RainLetters"
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion"



const Home = () => {


  return (

    <div className="flex min-h-screen flex-col items-center justify-between p-16 bg-foreground text-secondary">
      <div className="">
        <div className="w-1/2">
        {/* <RainLetters/> */}
        </div>
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

        <Accordion type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>What is genetic imputation?</AccordionTrigger>
            <AccordionContent className="max-w-4xl">
            Genetic imputation is a computational method used in human genomics to infer missing genetic 
variant information within a dataset. Leveraging reference panels with known genetic variants, 
the method utilizes statistical algorithms to estimate and fill in unobserved genetic markers.
            </AccordionContent>
          </AccordionItem>
        </Accordion>
        <Accordion type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>Why is there a security issue?</AccordionTrigger>
            <AccordionContent className="max-w-4xl">
            When genetic data is imputed, it may inadvertently reveal sensitive information 
about an individual's genetic makeup, ancestry, or health conditions. Additionally, By comparing 
reconstructed data with publicly available datasets or other sources of genetic information, 
malicious attackers may be able to re-identify individuals, linking their 
genetic data to their real-world identities.
            </AccordionContent>
          </AccordionItem>
        </Accordion>
        <Accordion type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>What is differential privacy?</AccordionTrigger>
            <AccordionContent className="max-w-4xl">
            Differential privacy is a privacy-preserving framework for data analysis that adds 
controlled noise to individual data points. It makes it extremely difficult to 
infer sensitive information about any specific individual while still providing accurate 
aggregate information from the dataset. It ensures that the presence or absence of any single 
data point does not significantly impact the overall results, safeguarding individual privacy 
in statistical analysis.
            </AccordionContent>
          </AccordionItem>
        </Accordion>
        <Accordion type="single" collapsible>
          <AccordionItem value="item-1">
            <AccordionTrigger>How are we applying differential privacy to genetic imputation security?</AccordionTrigger>
            <AccordionContent className="max-w-4xl">
            As the number of people's DNA being sequenced grows, the possibility of reconstruction attacks 
increases alongside it. We at Safe Impute seek to not just protect people's privacy but to protect
the very essence that makes us who we are. Through applying differential privacy to the dataset
genetic imputation uses to impute, we'll be able to protect individual's genetic code while keeping
the quality of the resulting imputed data high.
            </AccordionContent>
          </AccordionItem>
        </Accordion>
        
      </div>
    </div>
  )
}

export default Home













