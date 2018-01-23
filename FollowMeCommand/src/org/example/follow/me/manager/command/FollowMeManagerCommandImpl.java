    package org.example.follow.me.manager.command;
     
    import org.apache.felix.ipojo.annotations.Component;
    import org.apache.felix.ipojo.annotations.Instantiate;
    import org.apache.felix.ipojo.annotations.Requires;
    import org.example.follow.me.manager.FollowMeAdministration;
    import org.example.follow.me.manager.IlluminanceGoal;
    import org.example.follow.me.manager.EnergyGoal;
     
    import fr.liglab.adele.icasa.command.handler.Command;
    import fr.liglab.adele.icasa.command.handler.CommandProvider;
     
    //Define this class as an implementation of a component :
    @Component
    //Create an instance of the component
    @Instantiate(name = "follow.me.manager.command")
    //Use the handler command and declare the command as a command provider. The
    //namespace is used to prevent name collision.
    @CommandProvider(namespace = "followme")
    public class FollowMeManagerCommandImpl {
     
        // Declare a dependency to a FollowMeAdministration service
        @Requires
        private FollowMeAdministration m_administrationService;
     
     
        /**
         * Felix shell command implementation to sets the illuminance preference.
         *
         * @param goal the new illuminance preference ("SOFT", "MEDIUM", "FULL")
         */
     
        // Each command should start with a @Command annotation
        @Command
        public void setIlluminancePreference(String goal) {
            // The targeted goal
            IlluminanceGoal illuminanceGoal=IlluminanceGoal.FULL;
     
            // Here you have to convert the goal string into an illuminance
            // goal and fail if the entry is not "SOFT", "MEDIUM" or "HIGH"
            if (goal.equals("MEDIUM")){
            	illuminanceGoal= IlluminanceGoal.MEDIUM;
            }
            else if (goal.equals("SOFT")){
            	illuminanceGoal= IlluminanceGoal.SOFT;
            }
            else if (goal.equals("FULL")){
            	illuminanceGoal= IlluminanceGoal.SOFT;
            }
            else{
            	System.out.println("Error, enter SOFT, FULL or MEDIUM.");
            }
           
            
     
            //call the administration service to configure it :
            m_administrationService.setIlluminancePreference(illuminanceGoal);
        }
     
        @Command
        public void getIlluminancePreference(){
            // implement the command that print the current value of the goal
            System.out.println("The illuminance goal is "); 
            IlluminanceGoal illumGoal = m_administrationService.getIlluminancePreference();
            if(illumGoal.equals(IlluminanceGoal.FULL)){
            	System.out.println("FULL");
            }
            else if(illumGoal.equals(IlluminanceGoal.MEDIUM)){
            	System.out.println("MEDIUM");
            }
            else if(illumGoal.equals(IlluminanceGoal.SOFT)){
            	System.out.println("SOFT");
            }
            else{
            	System.out.println("Error");
            }
        }
        
        /**
         * Felix shell command implementation to sets the power consumption preference.
         *
         * @param goal the new power consumption preference ("LOW", "MEDIUM", "HIGH")
         */
        @Command
        public void setPowerConsumptionPreference(String goal) {
            EnergyGoal energyGoal=EnergyGoal.HIGH;
            if (goal.equals("MEDIUM")){
            	energyGoal= EnergyGoal.MEDIUM;
            }
            else if (goal.equals("HIGH")){
            	energyGoal= energyGoal.HIGH;
            }
            else if (goal.equals("LOW")){
            	energyGoal= energyGoal.LOW;
            }
            else{
            	System.out.println("Error, enter HIGH, MEDIUM or LOW");
            }
           
            //call the administration service to configure it :
            m_administrationService.setEnergySavingGoal(energyGoal);;
        }
     
        @Command
        public void getPowerConsumptionPreference(){
            // implement the command that print the current value of the goal
            System.out.println("The illuminance goal is "); 
            EnergyGoal energGoal = m_administrationService.getEnergyGoal();
            if(energGoal.equals(EnergyGoal.HIGH)){
            	System.out.println("HIGH");
            }
            else if(energGoal.equals(EnergyGoal.MEDIUM)){
            	System.out.println("MEDIUM");
            }
            else if(energGoal.equals(EnergyGoal.LOW)){
            	System.out.println("LOW");
            }
            else{
            	System.out.println("Error");
            }
        }
     
     
    }
