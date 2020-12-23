//package com.pachetepachete.Models;
//
//import com.pachetepachete.utils.Pair;
//
//import java.util.*;
//
//public abstract class Consumer implements Comparator<Consumer> {
//    private Resume resume;
//    private ArrayList<Consumer> friends;
//
//    public Consumer() {
//        this.friends = new ArrayList<>();
//    }
//
//    public void add(Education education) {
//        if (!this.studies.contains(education)) {
//            this.studies.add(education);
//        }
//    }
//
//    public void add(Experience experience) {
//        if (!this.experiences.contains(experience)) {
//            this.experiences.add(experience);
//        }
//    }
//
//    public void add(Consumer consumer) {
//        if (!this.friends.contains(consumer)) {
//            this.friends.add(consumer);
//        }
//    }
//
//    public int getDegreeInFriendship(Consumer consumer) {
//        LinkedList<Pair<Consumer, Integer>> queue = new LinkedList<>();
//        queue.add(new Pair<>(this, 0));
//        HashSet<Consumer> visited = new HashSet<>();
//
//
//        while (!queue.isEmpty()) {
//            Consumer con = queue.getFirst().getKey();
//            Integer lvl = queue.getFirst().getValue();
//            visited.add(con);
//            queue.pollFirst();
//
//            if (compare(con, consumer) == 0) {
//                return lvl;
//            }
//
//            for (Consumer friend : con.friends) {
//                if (!visited.contains(friend)) {
//                    queue.add(new Pair<>(friend, lvl + 1));
//                }
//            }
//        }
//
//        return -1;
//    }
//
//    public void remove(Consumer consumer) {
//        this.friends.remove(consumer);
//    }
//
//    public Integer getGraduationYear() {
//
//    }
//
//    public static class Resume {
//        private Information information;
//        private ArrayList<Education> educations;
//        private ArrayList<Experience> experiences;
//
//        private Resume(ResumeBuilder resumeBuilder) {
//            this.information = resumeBuilder.information;
//            this.educations = resumeBuilder.educations;
//            this.experiences = resumeBuilder.experiences;
//        }
//
//        public Information getInformation() {
//            return information;
//        }
//
//        public void setInformation(Information information) {
//            this.information = information;
//        }
//
//        public ArrayList<Education> getEducations() {
//            return educations;
//        }
//
//        public void setEducations(ArrayList<Education> educations) {
//            this.educations = educations;
//        }
//
//        public ArrayList<Experience> getExperiences() {
//            return experiences;
//        }
//
//        public void setExperiences(ArrayList<Experience> experiences) {
//            this.experiences = experiences;
//        }
//
//        public static class ResumeBuilder {
//            private Information information;
//            private ArrayList<Education> educations;
//            private ArrayList<Experience> experiences;
//
//            public ResumeBuilder() {
//                this.educations = new ArrayList<Education>();
//                this.experiences = new ArrayList<Experience>();
//            }
//
//            public ResumeBuilder information(Information information) {
//                this.information = information;
//                return this;
//            }
//
//            public ResumeBuilder education(ArrayList<Education> educations) {
//                this.educations = educations;
//                return this;
//            }
//
//            public ResumeBuilder experience(ArrayList<Experience> experiences) {
//                this.experiences = experiences;
//                return this;
//            }
//
//            public Resume build() {
//                return new Resume(this);
//            }
//
//        }
//    }
//
//}
