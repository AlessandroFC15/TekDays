import com.tekdays.TekEvent

class BootStrap {

    def init = { servletContext ->
        def event1 = new TekEvent(name: 'UFC 202', city: 'Saint Louis, MO',
                organizer: 'John Doe',
                venue: 'TBD',
                startDate: new Date('11/21/2013'),
                endDate: new Date('11/21/2013'),
                description: """This conference will bring coders from
            across platforms, languages, and industries
            together for an exciting day of tips, tricks,
            and tech! Stay sharp! Stay at the top of your
            game! But, don't stay home! Come an join us
            this fall for the first annual Gateway Code
            Camp.""")

        if (! event1.save()) {
            event1.errors.allErrors.each{ error ->
                println "An error occured with event1: $error"
            }
        }

        def event2 = new TekEvent(name: 'UFC 202', city: 'Las Vegas', organizer: 'Dana White', venue: 'MGM',
                                startDate: new Date('08/12/2016'), endDate: new Date('08/13/2016'),
                description: "We're not here to take part, we are here to take over!")

        if (! event2.save()) {
            event2.errors.allErrors.each {error ->
                println "An error occured with event2: $error"
            }
        }

    }
    def destroy = {
    }
}
