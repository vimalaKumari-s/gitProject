package com.example.demo;

import com.example.demo.utilities.Url;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

		@RestController
		public class GitController {

			@GetMapping("/branch")
			public String getBranches(@RequestParam(value = "url") String url) throws GitAPIException{
				List<String> branches = fetchGitBranches(url);
				return branches.toString();
			}

			@PostMapping(value = "/branches")
			public List<String> getBranchesByPost(@RequestBody Url url) throws GitAPIException {
				List<String> branches = fetchGitBranches(url.getUrl());
				return branches;
			}
			Boolean validateReg(String commitMessage){
				String regex = "*(:)*";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(commitMessage);
				return m.matches();
			}

			@PostMapping("/validateCommits")
			public boolean validateCommits(@RequestBody Url url ) throws GitAPIException, IOException {
				Boolean valid = true;
				LsRemoteCommand command= Git.lsRemoteRepository()
						.setRemote(url.getUrl())
						.setHeads(true);
						File file = new File("/Users/vimalakumari/Downloads/demo");

				Repository repo = new RepositoryBuilder().setWorkTree(file).build();
				Iterator<RevCommit> latestCommit = new Git(repo).
						log().
						setMaxCount(3).
						call()
						.iterator();
				Git git = new Git(repo);
				while(latestCommit.hasNext()){
					System.out.println(latestCommit.next().getFullMessage());
				}
//				List<Ref> ref = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
//				RevWalk walk = new RevWalk(git.getRepository());
//				RevCommit commit = walk.parseCommit(ref.get(0).getObjectId());
//				String latestCommitHash = commit.getFullMessage();
//				System.out.println(latestCommitHash);
				return valid;
			}
			public  List<String> fetchGitBranchesTwo(String gitUrl)  {
				Collection<Ref> refs;
				List<String> branches = new ArrayList<String>();
				try {
					File file = new File("/Users/vimalakumari/Desktop/path/to/repo");

					Git git = Git.cloneRepository()
						.setBare( true )
						.setURI( gitUrl)
						.setGitDir(file)
							.setCloneAllBranches(true)
							.call();
					List<Ref> list = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
					list.stream().map(e->branches.add(e.getLeaf().getName()));
					return branches;
				} catch (InvalidRemoteException e) {
					System.out.println(" InvalidRemoteException occurred in fetchGitBranches");
					e.printStackTrace();
				} catch (TransportException e) {
					System.out.println(" TransportException occurred in fetchGitBranches");

				} catch (GitAPIException e) {
					System.out.println(" GitAPIException occurred in fetchGitBranches");
				}
				return branches;
			}

			public  List<String> fetchGitBranches(String gitUrl)
			{
				Collection<Ref> refs;
				List<String> branches = new ArrayList<String>();
				try {

					refs = Git.lsRemoteRepository()
							.setHeads(true)
							.setRemote(gitUrl)
							.setCredentialsProvider( new UsernamePasswordCredentialsProvider( "vimalaKumari-s", "Vimala@bindu98") )
							.call();
					for (Ref ref : refs) {
						branches.add(ref.getName().substring(ref.getName().lastIndexOf("/")+1, ref.getName().length()));
					}

				} catch (InvalidRemoteException e) {
					System.out.println(" InvalidRemoteException occurred in fetchGitBranches");
					e.printStackTrace();
				} catch (TransportException e) {
					System.out.println(" TransportException occurred in fetchGitBranches");

				} catch (GitAPIException e) {
					System.out.println(" GitAPIException occurred in fetchGitBranches");

				}
				return branches;
			}

		}


//			public  List<String> fetchGitBranches(String gitUrl)
//			{
//				Collection<Ref> refs;
//				List<String> branches = new ArrayList<String>();
//				try {
//					refs = Git.lsRemoteRepository()
//							.setHeads(true)
//							.setRemote(gitUrl)
//							.call();
//					for (Ref ref : refs) {
//						branches.add(ref.getName().substring(ref.getName().lastIndexOf("/")+1, ref.getName().length()));
//					}
//
//				} catch (InvalidRemoteException e) {
//					System.out.println(" InvalidRemoteException occurred in fetchGitBranches");
//					e.printStackTrace();
//				} catch (TransportException e) {
//					System.out.println(" TransportException occurred in fetchGitBranches");
//				} catch (GitAPIException e) {
//					System.out.println(" GitAPIException occurred in fetchGitBranches");
//				}
//				return branches;
//			}
//
//	}




}

/*
1.support private git repo
2.send response in a meaningful way
3. last 5 commits, check whether they are following the pattern
 */