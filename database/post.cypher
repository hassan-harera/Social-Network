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
MATCH (u:User)-[r1:HAS_REACTED]->(re:React)
MATCH (p:Post)-[r2:HAS_REACT]->(re:React)
RETURN re

// count post likes
MATCH (u:User)-[r:LIKED]->(p:Post)
  WHERE id(p) = 3
RETURN count(r)

// delete like post
MATCH (u:User)-[r:LIKED]->(p:Post)
  WHERE id(u) = 0 AND id(p) = 3
DELETE r

// write a comment to a post
MATCH (u:User)
MATCH (p:Post)
  WHERE id(u) = 1 AND id(p) = 3
MERGE (u)-[r:COMMENTED]->(p)
SET r.datetime = datetime()
SET r.comment = 'This is a comment'
RETURN p

// delete a comment to a post
MATCH (c:Comment)-[ra:ACTED_ON]->(p:Post)
MATCH (u:User)-[rc:COMMENTED]->(c)
  WHERE id(p) = 3 AND id(c) = 6
DELETE ra, rc, c


// write a comment to a post
MATCH (u:User)
MATCH (c:Comment)
  WHERE id(u) = 1 AND id(c) = 3
MERGE (u)-[r:COMMENTED]->(c)
RETURN c

// count post comments
MATCH (p:Post)-[r:HAS_COMMENT]->(c:Comment)
  WHERE id(p) = 0
RETURN count (c)

// delete a post
MATCH (p1:Post)-[r1]->(a)
MATCH (b)-[r2]->(p2:Post)
  WHERE id(p) = 8
DELETE r1, r2, p;

MATCH (b)-[r2]->(p:Post)
  WHERE id(p) = 8
DELETE r2;

MATCH (p:Post)
  WHERE id(p) = 8
DELETE p
