package com.example.demo;

import com.example.demo.exceptions.ExceptionHelper;
import com.example.demo.utilities.LoginDetails;
import com.example.demo.utilities.Url;
import com.example.demo.utilities.UtilityMethods;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

		@RestController
		public class GitController {

		     @RequestMapping("/")
			 public String defaultM(){
				 return "hello";
			 }

			@PostMapping("/validateCommits")
			public boolean validateCommits(@RequestBody LoginDetails loginDetails) throws GitAPIException, IOException {
				LsRemoteCommand command = Git.lsRemoteRepository()
						.setHeads(true)
						.setRemote(loginDetails.getUrl());
//			    File file = new File("/Users/vimalakumari/Downloads/demo");
//				Repository repo = new RepositoryBuilder().setWorkTree(file).build();
				Repository repo  = command.getRepository();
				Iterator<RevCommit> latestCommit = new Git(repo).
						log().
						setMaxCount(3).
						call().
						iterator();

				while(latestCommit.hasNext()){
					if(!UtilityMethods.validateReg(latestCommit.next().getFullMessage()))
						return false;
				}
				return true;
			}

			@PostMapping("/issue")
			public void checkDefault(){
			System.out.println("lets see");
			}

		@PostMapping(value = "/branches")
		public ResponseEntity<Object> fetchGitBranchesWithErrors(@RequestBody LoginDetails loginDetails)
		{
			Collection<Ref> refs;
			List<String> branches = new ArrayList<String>();
			try {
				LsRemoteCommand command = Git.lsRemoteRepository()
						.setHeads(true)
						.setRemote(loginDetails.getUrl());
				if (loginDetails.getUserName()!=null && loginDetails.getAccessToken()!=null)
					command.setCredentialsProvider( new UsernamePasswordCredentialsProvider( loginDetails.getUserName(), loginDetails.getAccessToken()) );

					refs=command.call();

				for (Ref ref : refs) {
					branches.add(ref.getName().substring(ref.getName().lastIndexOf("/")+1, ref.getName().length()));
				}

			} catch (InvalidRemoteException e) {
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (TransportException e) {
				HashMap<String, String> issues = new HashMap<>();
				issues.put("Error:message",e.getMessage());
				return new ResponseEntity<Object>(issues, HttpStatus.BAD_REQUEST);
			} catch (GitAPIException e) {
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return  new ResponseEntity<Object>(branches,HttpStatus.OK);
		}

		}

}

/*
1.support private git repo
2.send response in a meaningful way
3. last 5 commits, check whether they are following the pattern
 */