package org.danekja.discussment.core.service;

import org.danekja.discussment.core.domain.Category;
import org.danekja.discussment.core.domain.Topic;

import java.util.List;

/**
 * Created by Martin Bláha on 13.05.17.
 *
 * The interface contains service methods for working with the topics
 */
public interface TopicService {

    /**
     * Add a new topic to the discussion without category
     *
     * @param topic new topic
     * @return new topic
     */
    Topic createTopic(Topic topic);

    /**
     * Add a new topic to the discussion with category
     *
     * @param topic new topic
     * @param category category of the topic
     * @return new topic
     */
    Topic createTopic(Topic topic, Category category);

    /**
     * Get a topic in the discussion based on its id.
     *
     * @param topicId topic id
     * @return topic by id
     */
    Topic getTopicById(long topicId);

    /**
     * Get all topics in the discussion based on its category.
     *
     * @param category category of the topic
     * @return list of Topic
     */
    List<Topic> getTopicsByCategory(Category category);

    /**
     * Get all topics in a discussion without a category.
     *
     * @return list of Topic
     */
    List<Topic> getTopicsWithoutCategory();

    /**
     * Remove the topic in the discussion
     *
     * @param topic topic to remove
     */
    void removeTopic(Topic topic);
}
