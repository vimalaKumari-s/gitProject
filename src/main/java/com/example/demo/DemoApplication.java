package com.example.demo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

		@RestController
		public class GitController {

			@GetMapping("/branch")
			public String getBranches(@RequestParam(value = "url") String url) throws GitAPIException {
				List<String> branches = fetchGitBranches(url);
				return branches.toString();
			}

			@PostMapping(value = "/branches")
			public List<String> getBranchesByPost(@RequestBody Url url) throws GitAPIException {
				List<String> branches = fetchGitBranches(url.getUrl());
				return branches;
			}
			public  List<String> fetchGitBranchesTwo(String gitUrl)
			{
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
					System.out.println(" InvalidRemoteException occured in fetchGitBranches");
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

}

