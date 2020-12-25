package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.InvalidGPAException;
import com.pachetepachete.Exceptions.UnknownEndDateException;
import com.pachetepachete.utils.Pair;

import java.util.*;

public abstract class Consumer implements Comparator<Consumer> {
    private Resume resume;
    private ArrayList<Consumer> friends;
    private ArrayList<Notification> notifications;

    public Consumer() {
        this.friends = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public Consumer(Consumer consumer) {
        this.resume = consumer.resume;
        this.friends = consumer.friends;
        this.notifications = consumer.notifications;
    }

    public void addNotification(Notification notification) {
        if (!this.notifications.contains(notification)) {

            for (Notification notification1 : this.notifications) {
                if (notification.getMessage().equals(notification1.getMessage())) {
                    return;
                }
            }

            this.notifications.add(notification);
        }
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<Consumer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Consumer> friends) {
        this.friends = friends;
    }

    public void add(Education education) {
        if (this.resume == null) {
            return;
        }

        if (!this.resume.getEducations().contains(education)) {
            this.resume.add(education);
        }
    }

    public void add(Experience experience) {
        if (this.resume == null) {
            return;
        }

        if (!this.resume.getExperiences().contains(experience)) {
            this.resume.add(experience);
        }
    }

    public void add(Consumer consumer) {
        if (!this.friends.contains(consumer)) {
            this.friends.add(consumer);
        }
    }

    public boolean remove(Experience experience) {
        if (this.resume == null) {
            return false;
        }

        return this.resume.remove(experience);
    }

    public boolean remove(Education education) {
        if (this.resume == null) {
            return false;
        }

        return this.resume.remove(education);
    }

    public boolean modify(Experience experience) {
        if (this.resume == null) {
            return false;
        }

        return this.resume.modify(experience);
    }

    public boolean modify(Education education) {
        if (this.resume == null) {
            return false;
        }

        return this.resume.modify(education);
    }

    public int getDegreeInFriendship(Consumer consumer) {
        LinkedList<Pair<Consumer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(this, 0));
        HashSet<Consumer> visited = new HashSet<>();


        while (!queue.isEmpty()) {
            Consumer con = queue.getFirst().getKey();
            Integer lvl = queue.getFirst().getValue();
            visited.add(con);
            queue.pollFirst();

            if (con == consumer || compare(con, consumer) == 0) {
                return lvl;
            }

            for (Consumer friend : con.friends) {
                if (!visited.contains(friend)) {
                    queue.add(new Pair<>(friend, lvl + 1));
                }
            }
        }

        return -1;
    }

    public void remove(Consumer consumer) {
        this.friends.remove(consumer);
    }

    public Integer getGraduationYear() {
        if (this.resume.getEducations() == null) {
            return -1;
        }

        Education ed = this.resume.getEducations().get(0);

        try {
            if (ed.getEnd() == null) {
                throw new UnknownEndDateException("Data de final este null si nu putem gasii un endDate valid. INVALID");
            }
        } catch (UnknownEndDateException e) {
            e.printStackTrace();
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ed.getEnd());

        return calendar.get(Calendar.YEAR);
    }

    public Double meanGPA() {
        if (this.resume.getEducations() == null) {
            return 0.0;
        }

        double sum = 0.0;
        int counter = 0;

        for (Education ed : this.resume.getEducations()) {
            if (ed.getMedie() != null) {
                sum = sum + ed.getMedie();
                counter ++;
            }
        }

        try {
            if (counter == 0) {
                throw new InvalidGPAException("Data de final este null si nu putem gasii un endDate valid. INVALID");
            }
        } catch (InvalidGPAException e) {
            e.printStackTrace();
            return -1.0;
        }

        return sum / counter;

    }

    @Override
    public int compare(Consumer o1, Consumer o2) {
        if (o1 == o2 || (o1.getResume() == null && o2.getResume() == null)) {
            return 0;
        }

        if (o1.getResume() == null || o1.getResume().getInformation() == null) {
            return 1;
        }

        if (o2.getResume() == null || o2.getResume().getInformation() == null) {
            return -1;
        }

        return o1.getResume().getInformation().getEmail()
                .compareTo(o2.getResume().getInformation().getEmail());
    }

    @Override
    public String toString() {
        return "Consumer{ \n" +
                "resume=" + resume +
                ",\n friends=" + friends +
                "\n}";
    }

    //###################################################
    public static class Resume {
        private Information information;
        private ArrayList<Education> educations;
        private ArrayList<Experience> experiences;

        private Resume(ResumeBuilder resumeBuilder) {
            this.information = resumeBuilder.information;
            this.educations = resumeBuilder.educations;
            this.experiences = resumeBuilder.experiences;
        }

        public Information getInformation() {
            return information;
        }

        public void setInformation(Information information) {
            this.information = information;
        }

        public ArrayList<Education> getEducations() {
            return educations;
        }

        public void setEducations(ArrayList<Education> educations) {
            this.educations = educations;
        }

        public ArrayList<Experience> getExperiences() {
            return experiences;
        }

        public void setExperiences(ArrayList<Experience> experiences) {
            this.experiences = experiences;
        }

        public boolean add(Education education) {
            if (!this.educations.contains(education)) {
                boolean check = this.educations.add(education);

                if (!check) {
                    return false;
                }

                Collections.sort(this.educations);
                return true;
            }

            return false;
        }

        public boolean add(Experience experience) {
            if (!this.experiences.contains(experience)) {
                boolean check = this.experiences.add(experience);

                if (!check) {
                    return false;
                }

                Collections.sort(this.experiences);
                return true;
            }

            return false;
        }

        public boolean remove(Education education) {
            return this.educations.remove(education);
        }

        public boolean remove(Experience experience) {
            return this.experiences.remove(experience);
        }

        public boolean modify(Education education) {
            if (this.educations.contains(education)) {
                int index = this.educations.indexOf(education);
                this.educations.set(index, education.copy());
                return true;
            }

            return false;
        }

        public boolean modify(Experience experience) {
            if (this.experiences.contains(experience)) {
                int index = this.experiences.indexOf(experience);
                this.experiences.set(index, experience.copy());
                return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return "Resume{" +
                    "information=" + information.toString() +
                    ", educations=" + educations.toString() +
                    ", experiences=" + experiences.toString() +
                    '}';
        }

        public static class ResumeBuilder {
            private Information information;
            private ArrayList<Education> educations;
            private ArrayList<Experience> experiences;

            public ResumeBuilder() {
                this.educations = new ArrayList<Education>();
                this.experiences = new ArrayList<Experience>();
            }

            public ResumeBuilder information(Information information) {
                this.information = information;
                return this;
            }

            public ResumeBuilder education(ArrayList<Education> educations) {
                this.educations = educations;
                return this;
            }

            public ResumeBuilder experience(ArrayList<Experience> experiences) {
                this.experiences = experiences;
                return this;
            }

            public Resume build() {
                return new Resume(this);
            }

        }
    }

}
