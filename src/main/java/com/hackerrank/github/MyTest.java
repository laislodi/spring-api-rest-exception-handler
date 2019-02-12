package com.hackerrank.github;

import com.hackerrank.github.model.ActorEntity;
import com.hackerrank.github.model.EventEntity;
import com.hackerrank.github.model.RepoEntity;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.GitHubApiRestActorService;
import com.hackerrank.github.service.converter.domainconverterimpl.ActorConverter;
import com.hackerrank.github.service.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyTest {

    @Autowired
    private static EventRepository eventRepository;
    @Autowired
    private static ActorRepository actorRepository;
    @Autowired
    private static RepoRepository repoRepository;
    @Autowired
    private static ActorConverter actorConverter;

//    @Autowired
//    public MyTest(EventRepository eventRepository, ActorRepository actorRepository, RepoRepository repoRepository, ActorConverter actorConverter) {
//        this.eventRepository = eventRepository;
//        this.actorRepository = actorRepository;
//        this.repoRepository = repoRepository;
//        this.actorConverter = actorConverter;
//    }


    public static void main(String[] args) {

        instanciation();
        //findAllActorsByStreak();
    }

//    public static List<ActorEntity> findAllActorsByStreak() {
//        List<ActorEntity> actorList = actorRepository.findAll();
//        Map<ActorEntity, Long> actorStreakMap = new HashMap<>();
//
//        for (ActorEntity a : actorList) {
//            List<EventEntity> eventList = a.getEventEntityList();
//            eventList.sort(Comparator.comparing(EventEntity::getCreatedAt));
//
//            System.out.println(eventList);
//
//            // eventList.sort((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
//
//            // eventList.sort(new Comparator<EventEntity>() {
//            //    @Override
//            //    public int compare(EventEntity o1, EventEntity o2) {
//            //        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
//            //    }
//            //});
//
//            LocalDateTime previous = null;
//            Long count = 1L;
//            Long max = 0L;
//
//            for (EventEntity e : eventList) {
//                LocalDateTime createdAt = e.getCreatedAt().toLocalDateTime();
//
//                //LocalDateTime d = previous.toLocalDateTime();
//                //d.minusDays(1);
//
//                if (previous == null) {
//                    count++;
//                    previous = createdAt;
//                } else {
//                    LocalDateTime createdAtMinusOneDay = createdAt.minusDays(1);
//                    if (createdAtMinusOneDay.getDayOfMonth() == previous.getDayOfMonth() &&
//                            createdAtMinusOneDay.getMonth() == previous.getMonth() &&
//                            createdAtMinusOneDay.getYear() == previous.getYear()){
//                        count++;
//                        if (count > max) {
//                            max = count;
//                        }
//                    } else {
//                        count = 1L;
//                    }
//                }
//            }
//            actorStreakMap.put(a, max);
//
//        }
//        System.out.println(actorStreakMap);
//        return null;
//    }

    private static void instanciation() {
        ActorEntity a1 = new ActorEntity(1L, "laislodi_streak-3", "https://avatars.com/laislodi");
        RepoEntity r1a = new RepoEntity(10L, "laislodi/exercise/a", "https://github.com/laislodi/exercise/a/streak-3");
        RepoEntity r1b = new RepoEntity(11L, "laislodi/exercise/b", "https://github.com/laislodi/exercise/b/streak-3");
        RepoEntity r1c = new RepoEntity(12L, "laislodi/exercise/c", "https://github.com/laislodi/exercise/c/streak-3");
        EventEntity e1a = new EventEntity(20L, "PushEvent", a1, r1a, LocalDateTime.of(2015,10,3,6,13,31,0));
        EventEntity e1b = new EventEntity(21L, "PushEvent", a1, r1b, LocalDateTime.of(2015,10,4,6,13,31,0));
        EventEntity e1c = new EventEntity(22L, "PushEvent", a1, r1c, LocalDateTime.of(2015,10,5,6,13,31,0));
        ArrayList<EventEntity> events1 = new ArrayList<>();
        events1.add(e1a);
        events1.add(e1b);
        events1.add(e1c);
        a1.setEventEntityList(events1);
        r1a.setEvent(events1);
        r1b.setEvent(events1);
        r1c.setEvent(events1);

        ActorEntity a2 = new ActorEntity(2L, "foguinho", "https://avatars.com/foguinho");
        RepoEntity r2a = new RepoEntity(13L, "foguinho/exercise/a", "https://github.com/foguinho/exercise/a");
        RepoEntity r2b = new RepoEntity(14L, "foguinho/exercise/b", "https://github.com/foguinho/exercise/b");
        EventEntity e2a = new EventEntity(23L, "PushEvent", a2, r2a, LocalDateTime.of(2014,7,13,8,13,31,0));
        EventEntity e2b = new EventEntity(24L, "PushEvent", a2, r2b, LocalDateTime.of(2014,7,12,8,13,31,0));
        ArrayList<EventEntity> events2 = new ArrayList<>();
        events2.add(e2a);
        events2.add(e2b);
        a2.setEventEntityList(events2);
        r2a.setEvent(events2);
        r2b.setEvent(events2);

        ActorEntity a3 = new ActorEntity(3L, "gabrieldias", "https://avatars.com/gabrieldias");
        RepoEntity r3a = new RepoEntity(15L, "gabrieldias/aperiam-consectetur/a", "https://github.com/iholloway/aperiam-consectetur/a");
        RepoEntity r3b = new RepoEntity(16L, "gabrieldias/aperiam-consectetur/b", "https://github.com/iholloway/aperiam-consectetur/b");
        EventEntity e3a = new EventEntity(25L, "PushEvent", a3, r3a, LocalDateTime.of(2016,4,18,0,13,31,0));
        EventEntity e3b = new EventEntity(26L, "PushEvent", a3, r3b, LocalDateTime.of(2016,4,17,0,13,31,0));
        ArrayList<EventEntity> events3 = new ArrayList<>();
        events3.add(e3a);
        events3.add(e3b);
        a3.setEventEntityList(events3);
        r3a.setEvent(events3);
        r3b.setEvent(events3);

//        ActorEntity a4 = new ActorEntity(3698252L, "daniel51", "https://avatars.com/3698252");
//        RepoEntity r4a = new RepoEntity(451024L, "daniel51/quo-tempore-dolor/a", "https://github.com/daniel51/quo-tempore-dolor/a");
//        RepoEntity r4b = new RepoEntity(451025L, "daniel51/quo-tempore-dolor/b", "https://github.com/daniel51/quo-tempore-dolor/b");
//        RepoEntity r4c = new RepoEntity(451026L, "daniel51/quo-tempore-dolor/c", "https://github.com/daniel51/quo-tempore-dolor/c");
//        RepoEntity r4d = new RepoEntity(451027L, "daniel51/quo-tempore-dolor/d", "https://github.com/daniel51/quo-tempore-dolor/d");
//        EventEntity e4a = new EventEntity(1514531484L, "PushEvent", a4, r4a, new Timestamp(2013,6,16,2,13,31,0));
//        EventEntity e4b = new EventEntity(1514531484L, "PushEvent", a4, r4b, new Timestamp(2013,6,17,2,13,31,0));
//        EventEntity e4c = new EventEntity(1514531484L, "PushEvent", a4, r4c, new Timestamp(2013,6,14,2,13,31,0));
//        EventEntity e4d = new EventEntity(1514531484L, "PushEvent", a4, r4d, new Timestamp(2013,6,15,2,13,31,0));
//        ArrayList<EventEntity> events4 = new ArrayList<>();
//        events4.add(e4a);
//        events4.add(e4b);
//        events4.add(e4c);
//        events4.add(e4d);
//        a4.setEventEntityList(events4);
//        r4a.setEvent(events4);
//        r4b.setEvent(events4);
//        r4c.setEvent(events4);
//        r4c.setEvent(events4);

        List<ActorEntity> allActorEntities = new ArrayList<>();
        allActorEntities.add(a1);
        allActorEntities.add(a2);
        allActorEntities.add(a3);

        GitHubApiRestActorService gitHubApiRestActorService = new GitHubApiRestActorService(eventRepository, actorRepository, repoRepository, actorConverter);

        List<Actor> allActorsByStreak = gitHubApiRestActorService.findAllActorsByStreak();

        System.out.println(allActorsByStreak);
    }
}
