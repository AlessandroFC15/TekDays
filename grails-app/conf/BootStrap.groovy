import com.tekdays.*

class BootStrap {

    def init = { servletContext ->

        addUsers()

        def event1 = new TekEvent(name: 'UFC 201', city: 'Saint Louis, MO',
                organizer: TekUser.findByUserName("tractorman"),
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

        def event2 = new TekEvent(name: 'UFC 202', city: 'Las Vegas', organizer: TekUser.findByUserName('elchapojr'), venue: 'MGM',
                                startDate: new Date('08/12/2016'), endDate: new Date('08/13/2016'),
                description: "We're not here to take part, we are here to take over!")

        if (! event2.save()) {
            event2.errors.allErrors.each {error ->
                println "An error occured with event2: $error"
            }
        }

        addVolunteers()

        addSponsors(event2)
    }

    def addUsers() {
        new TekUser(fullName: 'John Deere',
                        userName: 'tractorman',
                        password: 't0ps3cr3t',
                        email: 'john.deere@porkproducers.org',
                        website: 'www.perl.porkproducers.org',
                        bio: '''John is a top notch Perl programmer and a pretty
                        good hand around the farm. If he can't program it he
                        can plow it!''').save()

        new TekUser(fullName: 'Conor McGregor',
                        userName: 'elchapojr',
                        password: 'too_powerful',
                        email: 'mysticmac@ufc.com',
                        website: 'www.thenotorious.one',
                        bio: '''The GOAT!''').save()
    }

    def addVolunteers() {
        def g1 = TekEvent.findByName('UFC 202')
        g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
                userName: 'sarah',
                password: '54321',
                email: 'sarah@martinworld.com',
                website: 'www.martinworld.com',
                bio: 'Web designer and Grails afficianado.'))
        g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                userName: 'Mr_Bill',
                password: '12345',
                email: 'mrbill@email.com',
                website: 'www.mrbillswebsite.com',
                bio: 'Software developer, claymation artist.'))

        g1.addToRespondents('ben@grailsmail.com')
        g1.addToRespondents('zachary@linuxgurus.org')
        g1.addToRespondents('solomon@bootstrapwelding.com')

        g1.save()
    }

    def addSponsors(TekEvent g1) {
        def s1 = new Sponsor(name:'Contegix',
                website:'http://www.contegix.com',
                description:'Beyond Managed Hosting for your Enterprise').save()

        def s2 = new Sponsor(name:'Object Computing Incorporated',
                website:'http://ociweb.com',
                description:'An OO Software Engineering Company').save()

        def sp1 = new Sponsorship(event:g1,
                sponsor:s1,
                contributionType:'Other',
                description:'Cool T-Shirts').save()

        def sp2 = new Sponsorship(event:g1,
                sponsor:s2,
                contributionType:'Venue',
                description:'Will be paying for the Moscone').save()

        /*g1.addToSponsorships()
        g1.addToSponsorships(sp2)
        g1.save()*/

    }

    def destroy = {
    }
}
