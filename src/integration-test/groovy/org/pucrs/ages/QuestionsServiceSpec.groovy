package org.pucrs.ages

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuestionsServiceSpec extends Specification {

    QuestionsService questionsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Questions(...).save(flush: true, failOnError: true)
        //new Questions(...).save(flush: true, failOnError: true)
        //Questions questions = new Questions(...).save(flush: true, failOnError: true)
        //new Questions(...).save(flush: true, failOnError: true)
        //new Questions(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //questions.id
    }

    void "test get"() {
        setupData()

        expect:
        questionsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Questions> questionsList = questionsService.list(max: 2, offset: 2)

        then:
        questionsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        questionsService.count() == 5
    }

    void "test delete"() {
        Long questionsId = setupData()

        expect:
        questionsService.count() == 5

        when:
        questionsService.delete(questionsId)
        sessionFactory.currentSession.flush()

        then:
        questionsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Questions questions = new Questions()
        questionsService.save(questions)

        then:
        questions.id != null
    }
}
