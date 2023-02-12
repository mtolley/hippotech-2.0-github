package com.hippotech.api.data;

import com.hippotech.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Seeder {
    private HippoUserRepository userRepository;
    private BlogRepository blogRepository;
    private ApprovalRepository approvalRepository;
    private FraudCheckRepository fraudCheckRepository;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Autowired
    public Seeder(HippoUserRepository userRepository,
                  ApprovalRepository approvalRepository,
                  FraudCheckRepository fraudCheckRepository,
                  BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.approvalRepository = approvalRepository;
        this.fraudCheckRepository = fraudCheckRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        HippoUser user1 = new HippoUser();
        user1.setFamilyName("Tolley");
        user1.setGivenName("Scott");
        user1.setEmail("siguser@synopsys.com");
        user1.setTitle("Mr");
        user1.setPassword("password");
        userRepository.save(user1);

        BlogPost blogPost1 = new BlogPost();
        blogPost1.setTitle("Where next for house prices?");
        blogPost1.setImage("blog1.jpg");
        blogPost1.setDescription("Boiling hot house prices in the Netherlands may be a sign of things to come in rich, densely populated countries.");
        blogPost1.setFullText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        blogPost1.setDate("2021-12-13T00:00:00.000Z");
        BlogPostComment blogPostComment1 = new BlogPostComment();
        blogPostComment1.setEmail("joost@ns.nl");
        blogPostComment1.setText("I could not disagree more. So I won't!");

        BlogPostComment blogPostComment2 = new BlogPostComment();
        blogPostComment2.setEmail("xavierb@synopsys.com");
        blogPostComment2.setText("Je suis entièrement d'accord avec ce sentiment.");

        List<BlogPostComment> blogPostComments= new ArrayList<BlogPostComment>();
        blogPostComments.add(blogPostComment1);
        blogPostComments.add(blogPostComment2);
        blogPost1.setComments(blogPostComments);

        blogRepository.save(blogPost1);

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setTitle("A Cornish treat");
        blogPost2.setImage("blog2.jpg");
        blogPost2.setDescription("LA small country estate nestled amongst 23 acres of rolling Cornish countryside, just a stone’s throw from Falmouth — for sale for £1.85 million.");
        blogPost2.setFullText("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.");
        blogPost2.setDate("2022-01-01T00:00:00.000Z");

        blogRepository.save(blogPost2);

        BlogPost blogPost3 = new BlogPost();
        blogPost3.setTitle("How to become a \"power buyer\" in 2022");
        blogPost3.setImage("blog3.jpg");
        blogPost3.setDescription("Are you thinking of moving this year? Buyers are facing hot competition right now, with a huge number of people chasing after every available property for sale. So if you’ve been looking to move, you’ve no doubt noticed how competitive it is.");
        blogPost3.setFullText("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn\\'t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.");
        blogPost3.setDate("2022-01-24T00:00:00.000Z");

        blogRepository.save(blogPost3);

        MortgageApprovalEvent mortgageApprovalEvent1 = new MortgageApprovalEvent();
        mortgageApprovalEvent1.setDate("2020-01-05T00:00:00.000Z");
        mortgageApprovalEvent1.setEvent("Created");
        mortgageApprovalEvent1.setParty("Customer");
        mortgageApprovalEvent1.setDetails("Submitted for approval.");

        MortgageApprovalEvent mortgageApprovalEvent2 = new MortgageApprovalEvent();
        mortgageApprovalEvent2.setDate("2020-02-15T00:00:00.000Z");
        mortgageApprovalEvent2.setEvent("Agreement in Principal");
        mortgageApprovalEvent2.setParty("HippoTech");
        mortgageApprovalEvent2.setDetails("Agreement in principal subject to lender checks and property valuation");

        List<MortgageApprovalEvent> history1 = new ArrayList<MortgageApprovalEvent>();
        history1.add(mortgageApprovalEvent1);
        history1.add(mortgageApprovalEvent2);

        ApprovalRequest approvalRequest1 = new ApprovalRequest();
        approvalRequest1.setAddress1("1 Alabaster Avenue");
        approvalRequest1.setStatus("Approved");
        approvalRequest1.setPurchasePrice(500000);
        approvalRequest1.setAmountToBorrow(400000);
        approvalRequest1.setHistory(history1);
        approvalRepository.save(approvalRequest1);

        MortgageApprovalEvent mortgageApprovalEvent3 = new MortgageApprovalEvent();
        mortgageApprovalEvent3.setDate("2020-02-17T00:00:00.000Z");
        mortgageApprovalEvent3.setEvent("Created");
        mortgageApprovalEvent3.setParty("Customer");
        mortgageApprovalEvent3.setDetails("Submitted for approval.");

        MortgageApprovalEvent mortgageApprovalEvent4 = new MortgageApprovalEvent();
        mortgageApprovalEvent4.setDate("2020-04-22T00:00:00.000Z");
        mortgageApprovalEvent4.setEvent("Agreement in Principal");
        mortgageApprovalEvent4.setParty("HippoTech");
        mortgageApprovalEvent4.setDetails("Agreement in principal subject to lender checks and property valuation");

        List<MortgageApprovalEvent> history2 = new ArrayList<MortgageApprovalEvent>();
        history2.add(mortgageApprovalEvent3);
        history2.add(mortgageApprovalEvent4);

        ApprovalRequest approvalRequest2 = new ApprovalRequest();
        approvalRequest2.setAddress1("35 Broad Street");
        approvalRequest2.setStatus("Approval Pending");
        approvalRequest2.setPurchasePrice(850000);
        approvalRequest2.setAmountToBorrow(700000);
        approvalRequest2.setHistory(history2);
        approvalRepository.save(approvalRequest2);

        FraudCheck fraudCheck = new FraudCheck();
        fraudCheck.setAddress1("Mason Avenue");
        fraudCheck.setName("Bernie Madoff");
        fraudCheck.setLoanValue(450000);
        fraudCheck.setZip("30012");
        fraudCheckRepository.save(fraudCheck);

        FraudCheck fraudCheck1 = new FraudCheck();
        fraudCheck1.setAddress1("Teller Drive");
        fraudCheck1.setName("Steve Smith");
        fraudCheck1.setLoanValue(323000);
        fraudCheck1.setZip("79652");
        fraudCheckRepository.save(fraudCheck1);

        FraudCheck fraudCheck2 = new FraudCheck();
        fraudCheck2.setAddress1("Watts-Frank St");
        fraudCheck2.setName("Scott Johnson");
        fraudCheck2.setLoanValue(600000);
        fraudCheck2.setZip("29312");
        fraudCheckRepository.save(fraudCheck2);

        FraudCheck fraudCheck3 = new FraudCheck();
        fraudCheck3.setAddress1("Plymouth St");
        fraudCheck3.setName("Elon Musk");
        fraudCheck3.setLoanValue(250000);
        fraudCheck3.setZip("11296");
        fraudCheckRepository.save(fraudCheck3);

        FraudCheck fraudCheck4 = new FraudCheck();
        fraudCheck4.setAddress1("Main St");
        fraudCheck4.setName("Elon Musk");
        fraudCheck4.setLoanValue(599000);
        fraudCheck4.setZip("76598");
        fraudCheckRepository.save(fraudCheck4);

        FraudCheck fraudCheck5 = new FraudCheck();
        fraudCheck5.setAddress1("Main St");
        fraudCheck5.setName("Elon Musk");
        fraudCheck5.setLoanValue(450000);
        fraudCheck5.setZip("93232");
        fraudCheckRepository.save(fraudCheck5);

        FraudCheck fraudCheck6 = new FraudCheck();
        fraudCheck6.setAddress1("Main Place");
        fraudCheck6.setName("Bernie Madoff");
        fraudCheck6.setLoanValue(450000);
        fraudCheck6.setZip("93232");
        fraudCheckRepository.save(fraudCheck6);
    }
}
