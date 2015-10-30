import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
public class JIRAClient {
	public static void main(String[] args)  {
		JerseyJiraRestClientFactory f = new JerseyJiraRestClientFactory();
		JiraRestClient jc = null;
		try {
			jc = f.createWithBasicHttpAuthentication(new URI("http://localhost:8080/"), "vel.vignesh@gmail.com1", "Pass@1234");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		SearchResult r = jc.getSearchClient().searchJql("type = Epic ORDER BY RANK ASC", null);
		Iterator<BasicIssue> it = r.getIssues().iterator();
		while (it.hasNext()) {
			Issue issue = jc.getIssueClient().getIssue(((BasicIssue)it.next()).getKey(), null);
			System.out.println("Epic: " + issue.getKey() + " " + issue.getSummary());
			Iterator<IssueLink> itLink = issue.getIssueLinks().iterator();
			/*while(itLink.hasNext()) {
				IssueLink issueLink = (IssueLink)itLink.next();
				Issue issueL = jc.getIssueClient().getIssue((issueLink).getTargetIssueKey(), null);
				System.out.println(issueLink.getIssueLinkType().getDescription() + ": " + issueL.getKey() + " " + issueL.getSummary());
		        }*/
		}
	}
	
}
