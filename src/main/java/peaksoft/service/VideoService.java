package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.VideoRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.VideoResponse;
import peaksoft.entity.Task;
import peaksoft.entity.Video;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.TaskRepository;
import peaksoft.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final TaskRepository taskRepository;

    public VideoResponse saveVideo(VideoRequest videoRequest) {
        Video video = mapToEntity(videoRequest);
        Task task = taskRepository.findById(videoRequest.getLessonId())
                .orElseThrow(() -> new NotFoundException("lesson with id: " + videoRequest.getLessonId() + " does not exists"));
        task.addVideo(video);
        videoRepository.save(video);
        return mapToView(video);

    }

    public VideoResponse findById(Long id) {
        Video video = getById(id);
        return mapToView(video);
    }

    public VideoResponse update(Long id, VideoRequest videoRequest) {
        Video video = getById(id);
        convertToUpdate(video, videoRequest);
        videoRepository.save(video);
        return mapToView(video);
    }

    public SimpleResponse deleteVideoId(Long id) {
        boolean exists = videoRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException("video with id " + id + " not found!");
        }
        videoRepository.deleteById(id);
        return new SimpleResponse(
                "DELETED",
                "video with id " + id + "deleted successfully"
        );
    }

    public List<VideoResponse> findAll() {
        return convertAllToView(videoRepository.findAll());
    }

    private Video getById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new NotFoundException("video with id: " + id + " not found!"));
    }

    public VideoResponse mapToView(Video video) {
        VideoResponse response = new VideoResponse();
        response.setId(video.getVideoId());
        response.setVideoName(video.getVideoName());
        response.setLink(video.getLink());
        return response;
    }

    public Video mapToEntity(VideoRequest videoRequest) {
        Video video = new Video();
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(video.getLink());
        return video;
    }

    public Video convertToUpdate(Video video, VideoRequest videoRequest) {
        video.setVideoName(videoRequest.getVideoName());
        video.setLink(videoRequest.getLink());
        video.setLesson(video.getLesson());
        return video;
    }

    public List<VideoResponse> convertAllToView(List<Video> videos) {
        List<VideoResponse> videoResponses = new ArrayList<>();
        for (Video video : videos) {
            videoResponses.add(mapToView(video));
        }
        return videoResponses;
    }
}