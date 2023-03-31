# acme-monolithic-project
 Monolithic project.

 ## Refatoração
 - Organized packages
 - Changed package name: repositories to repository
 - Applied Lombok to automate setter/getter generation
 - Add ModelMapper to avoid boilerplate code and to automate model transformations DTO
 - Removed all method without references
 

 - Others changes:
 
<pre>
├── ...
├── domain                    
    ├── model - [DONE]
    <span style="color: rgb(0,187,47)">// Removed AggregatedRating.java</span>
    <span style="color: rgb(0,187,47)">// Removed Rating.java</span>
        ├── Product.java    
        ├── Review.java         
        |    <span style="color: rgb(0,187,47)">// Created Enum type ApprovalStatus (PENDING, APPROVED, REJECTED)</span>
        |    <span style="color: rgb(0,187,47)">// Created attribute Double rate</span> 
        |    <span style="color: rgb(0,187,47)">// Change class User to String user</span>        
        |    <span style="color: rgb(0,187,47)">// Removed attribute funFact</span>    
        |    <span style="color: rgb(0,187,47)">// Change upVotes and downVotes to votes</span>       
        └── Votes.java 
        |    <span style="color: rgb(0,187,47)">// Change class Embeddable to Entity with voteId</span>   
        |    <span style="color: rgb(0,187,47)">// Change class User to String user</span>               
    ├── repository   
    <span style="color: rgb(0,187,47)">// Removed AggregatedRatingRepository.java</span>
        ├── ProductRepository.java    
        ├── ReviewRepository.java
        |    <span style="color: rgb(0,187,47)">// </span>
        └── VotesRepository.java      
    └── services  
    
├── dto                    
    ├── mapper  
    |   ├── ProductMapper.java  
    |   ├── ReviewMapper.java
    ├── request
    |   ├── ProductRequest.java  
    |   ├── ReviewRequest.java
    ├── response
    |   ├── ProductResponse.java  
    |   ├── ReviewResponse.java
    ├── ProductDetailDTO.java
    ├── ProductDTO.java
    └── VoteReviewDTO.java    
└── ...
</pre>