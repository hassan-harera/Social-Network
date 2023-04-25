MATCH (u:User)-[r :JOINED]->(g:Group)
  WHERE id(u) =~ 0 AND id(g) =~ 0
DELETE r

MATCH (u:User {id: 0})-[r]->(g:Group)
DELETE r

// like post
MATCH (u:User)
MATCH (p:Post)
  WHERE id(u) = 1 AND id(p) = 3
MERGE (u)-[r:LIKED]->(p)
SET r.datetime = datetime()
RETURN p

// get specific like
MATCH (u:User)-[r:LIKED]->(p:Post)
  WHERE id(u) = 1 AND id(p) = 3
RETURN r

// count post likes
MATCH (u:User)-[r:LIKED]->(p:Post)
  WHERE id(p) = 3
RETURN COUNT(r)

// unlike post
MATCH (u:User)-[r:LIKED]->(p:Post)
  WHERE id(u) = 0 AND id(p) = 3
DELETE r

// write a comment to a post
MATCH (u:User)
MATCH (p:Post)
  WHERE id(u) = 1 AND id(p) = 3
MERGE (u)-[r:COMMENTED]->(p)
SET r.datetime = datetime()
SET r.comment = "This is a comment"
RETURN p

// delete a comment to a post
MATCH (u:User)-[r:COMMENTED]->(p:Post)
  WHERE id(r) = 2
DELETE r
RETURN p

