package me.whiteship.refactoring.duplicated_code.my_tobe;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudyDashboard {
    private void getIssueAndPrintUserName(int eventId) throws IOException {
        GHIssue issue = getGhIssue(eventId);
        Set<String> userNames = issue.getComments().stream().map(GHIssueComment::getUserName).collect(Collectors.toCollection(HashSet::new));
        printUserName(userNames);
    }

    private GHIssue getGhIssue(int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    private void printUserName(Set<String> userNames) {
        userNames.forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();

        studyDashboard.getIssueAndPrintUserName(15);
        studyDashboard.getIssueAndPrintUserName(30);
    }
}
